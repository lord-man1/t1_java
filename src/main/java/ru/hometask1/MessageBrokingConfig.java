package ru.hometask1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hometask1.repository.EncourageRepository;
import ru.PubSubChannel;
import ru.PubSubInMemoryQueue;
import ru.Publisher;
import ru.RepositorySubscriber;

@Configuration
public class MessageBrokingConfig {
    @Bean
    public PubSubChannel<String> pubSubChannel() {
        return new PubSubInMemoryQueue<>();
    }

    @Bean
    public Publisher<String> publisher(PubSubChannel<String> channel) {
        return new Publisher<>(channel);
    }

    @Bean
    public Runnable subscriber(PubSubChannel<String> channel, EncourageRepository repository) {
        return new RepositorySubscriber<>(channel, repository);
    }
}
