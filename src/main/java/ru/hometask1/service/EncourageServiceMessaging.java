package ru.hometask1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import ru.Publisher;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;
import ru.hometask1.exception.JsonParsingException;
import ru.hometask1.repository.EncourageRepository;

@RequiredArgsConstructor
public class EncourageServiceMessaging implements EncourageService {
    private final EncourageRepository repository;
    private final Publisher<ExampleRequest> publisher;

    @Override
    public ExampleResponse findRandomPhrase() {
        return repository.getRandomPhrase();
    }

    @Override
    public void addPhrase(ExampleRequest phrase) {
        try {
            publisher.publish(phrase);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e.getMessage(), e);
        }
    }
}
