package ru.hometask1.repository;

import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;

public interface EncourageRepository {
    ExampleResponse getRandomPhrase();

    void addPhrase(ExampleRequest phrase);
}
