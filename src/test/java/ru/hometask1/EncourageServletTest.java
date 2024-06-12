package ru.hometask1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EncourageServletTest {
    private EncourageServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private EncourageServiceImpl service;

    @BeforeEach
    void setUp() throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        service = mock(EncourageServiceImpl.class);

        servlet = new EncourageServlet(service);
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void handleDoGet() throws IOException {
        String testPhrase = "Everything will be fine!";
        doReturn(testPhrase).when(service).getRandomPhrase();

        servlet.doGet(request, response);

        verify(service, times(1)).getRandomPhrase();
        assertEquals(stringWriter.toString().strip(), testPhrase);
    }

    @Test
    void handleDoPost() throws IOException {
        String testPhrase = "Everything will be fine!";
        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(testPhrase))
        );

        servlet.doPost(request, response);

        verify(service, times(1)).addPhrase(testPhrase);
    }
}
