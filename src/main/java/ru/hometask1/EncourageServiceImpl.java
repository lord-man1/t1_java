package ru.hometask1;

public class EncourageServiceImpl implements EncourageService {
    private final EncourageRepository repository;

    public EncourageServiceImpl() {
        this.repository = new InMemoryEncourageRepository();
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
