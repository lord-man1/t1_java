package ru.hometask1.repository;

public interface EncourageRepository {
    String getRandomPhrase();

    void addPhrase(String phrase);
}
