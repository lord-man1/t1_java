package ru.hometask1.repository;

import ru.Repository;

public interface EncourageRepository extends Repository<String> {
    String getRandomPhrase();

    void addPhrase(String phrase);
}
