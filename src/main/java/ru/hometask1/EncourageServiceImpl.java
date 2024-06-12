package ru.hometask1;

public class EncourageServiceImpl implements EncourageService {
    private final EncourageRepository repository;

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
}
