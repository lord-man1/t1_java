package ru.hometask1;

public interface EncourageRepository {
    String getRandomPhrase();

    void addPhrase(String phrase);
}
