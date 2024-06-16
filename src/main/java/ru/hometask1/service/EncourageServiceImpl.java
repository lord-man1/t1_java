package ru.hometask1.service;

import ru.hometask1.repository.EncourageRepository;

public class EncourageServiceImpl implements EncourageService {
    private EncourageRepository repository;

    public EncourageServiceImpl(EncourageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getRandomPhrase() {
        return repository.getRandomPhrase();
    }

    @Override
    public void addPhrase(String phrase) {
        repository.addPhrase(phrase);
    }

    public void setRepository(EncourageRepository repository) {
        this.repository = repository;
    }
}
