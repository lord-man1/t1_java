package ru.hometask1.controller;

import ru.hometask1.service.EncourageService;
import ru.hometask1.annotation.GetMapping;
import ru.hometask1.annotation.PostMapping;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;

public class EncourageController implements Controller {
    private EncourageService encourageService;

    public EncourageController(EncourageService service) {
        this.encourageService = service;
    }

    @Override
    @GetMapping("/hello")
    public ExampleResponse hello() {
        return new ExampleResponse("HELLLLOOOOO");
    }

    @GetMapping("/encourage-servlet")
    public ExampleResponse exampleGet() {
        return new ExampleResponse(encourageService.getRandomPhrase());
    }

    @PostMapping("/encourage-servlet")
    public void examplePost(ExampleRequest request) {
        encourageService.addPhrase(request.phrase());
    }

    public void setEncourageService(EncourageService encourageService) {
        this.encourageService = encourageService;
    }
}
