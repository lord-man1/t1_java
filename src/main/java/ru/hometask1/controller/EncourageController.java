package ru.hometask1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hometask1.service.EncourageService;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;
import ru.Publisher;

@RestController
@RequiredArgsConstructor
public class EncourageController {
    @Value("${spring.application.greeting-message}")
    private String greetingMessage;
    private final EncourageService encourageService;

    @GetMapping("/hello")
    public ExampleResponse hello() {
        return new ExampleResponse(greetingMessage);
    }

    @GetMapping("/encourage-servlet")
    public ExampleResponse exampleGet() {
        return encourageService.findRandomPhrase();
    }

    @PostMapping("/encourage-servlet")
    public void examplePost(@Valid @RequestBody ExampleRequest request) {
        encourageService.addPhrase(request);
    }
}
