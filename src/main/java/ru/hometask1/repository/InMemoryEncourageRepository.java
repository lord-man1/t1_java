package ru.hometask1.repository;

import org.springframework.stereotype.Repository;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.dto.ExampleResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryEncourageRepository implements EncourageRepository {
    private final Map<UUID, ExampleRequest> phrases = new ConcurrentHashMap<>(
            Map.of(UUID.randomUUID(), new ExampleRequest("У тебя всё получится!"),
                    UUID.randomUUID(), new ExampleRequest("Ты справишься!"),
                    UUID.randomUUID(), new ExampleRequest("Кто прочитал, тот счастлив!")
            ));

    @Override
    public ExampleResponse getRandomPhrase() {
        UUID[] uuids = phrases.keySet().toArray(new UUID[0]);
        return new ExampleResponse(
                phrases.get(uuids[(int) (Math.random() * uuids.length)])
                        .phrase()
        );
    }

    @Override
    public void addPhrase(ExampleRequest phrase) {
        phrases.put(UUID.randomUUID(), phrase);
    }
}
