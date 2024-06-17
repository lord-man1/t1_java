package ru.hometask1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final EncourageService encourageService;
    private final Publisher<String> publisher;

    @GetMapping("/hello")
    public ExampleResponse hello() {
        return new ExampleResponse("Hello, World!");
    }

    @GetMapping("/encourage-servlet")
    public ExampleResponse exampleGet() {
        return new ExampleResponse(encourageService.findRandomPhrase());
    }

    @PostMapping("/encourage-servlet")
    public void examplePost(@Valid @RequestBody ExampleRequest request) {
        publisher.publish(request.phrase());
    }
}
