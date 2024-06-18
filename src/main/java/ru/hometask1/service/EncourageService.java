package ru.hometask1.service;

import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;

public interface EncourageService {
    ExampleResponse findRandomPhrase();

    void addPhrase(ExampleRequest phrase);
}
