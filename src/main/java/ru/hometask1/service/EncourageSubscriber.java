package ru.hometask1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.Subscriber;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.repository.EncourageRepository;

@Component
@RequiredArgsConstructor
public class EncourageSubscriber {
    private final EncourageRepository repository;

    @Subscriber
    public void subscriber(ExampleRequest request) {
        repository.addPhrase(request);
    }
}
