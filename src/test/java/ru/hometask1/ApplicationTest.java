package ru.hometask1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;
import ru.hometask1.service.EncourageService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@DisplayName("Приложение должно ")
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @SpyBean
    private EncourageService service;

    @DisplayName("корректно возвращать приветственную фразу.")
    @Test
    public void shouldReturnCorrectGreetingPhrase(@Value("${spring.application.greeting-message}")
                                                  String greetingMessage) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(jsonPath("$.phrase")
                        .value(greetingMessage));
    }

    @DisplayName("корректно возвращать случайную фразу.")
    @Test
    public void shouldReturnCorrectRandomPhrase() throws Exception {
        final var randomPhraseResponse = new ExampleResponse("TEST_PHRASE");

        when(service.findRandomPhrase()).thenReturn(randomPhraseResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/encourage-servlet"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(mapper.writeValueAsString(randomPhraseResponse))
                );
    }

    @DisplayName("корректно принимать фразу и передавать его Publisher'у.")
    @Test
    public void shouldCorrectSaveRandomPhrase() throws Exception {
        final var phraseRequest = new ExampleRequest("TEST_PHRASE");
        final var jsonPhraseRequest = mapper.writeValueAsString(phraseRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/encourage-servlet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPhraseRequest));

        verify(service, times(1)).addPhrase(phraseRequest);
    }
}
