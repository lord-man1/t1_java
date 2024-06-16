package ru.hometask1.repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEncourageRepository implements EncourageRepository {
    private final Map<UUID, String> phrases = new ConcurrentHashMap<>(
            Map.of(UUID.randomUUID(), "У тебя всё получится!",
                    UUID.randomUUID(), "Ты справишься!",
                    UUID.randomUUID(), "Кто прочитал, тот счастлив!"
            ));

    @Override
    public String getRandomPhrase() {
        UUID[] uuids = phrases.keySet().toArray(new UUID[0]);
        return phrases.get(uuids[(int) (Math.random() * uuids.length)]);
    }

    @Override
    public void addPhrase(String phrase) {
        phrases.put(UUID.randomUUID(), phrase);
    }
}
