package ru.hometask1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hometask1.repository.EncourageRepository;

@Service
@RequiredArgsConstructor
public class EncourageServiceImpl implements EncourageService {
    private final EncourageRepository repository;

    @Override
    public String findRandomPhrase() {
        return repository.getRandomPhrase();
    }

    @Override
    public void addPhrase(String phrase) {
        repository.addPhrase(phrase);
    }
}
