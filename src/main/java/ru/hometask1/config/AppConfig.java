package ru.hometask1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.MessageBroker;
import ru.Publisher;
import ru.config.MessageBrokingConfigProperties;
import ru.hometask1.dto.ExampleRequest;
import ru.hometask1.repository.EncourageRepository;
import ru.hometask1.service.EncourageService;
import ru.hometask1.service.EncourageServiceMessaging;

@ConditionalOnProperty(prefix = "spring.application.messaging", name = "enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties(MessageBrokingConfigProperties.class)
public class AppConfig {
    @Bean
    public Publisher<ExampleRequest> publisher(MessageBroker channel, ObjectMapper mapper) {
        return new Publisher<>(channel, mapper);
    }

    @Bean
    @Primary
    public EncourageService messagingService(EncourageRepository repository, Publisher<ExampleRequest> publisher) {
        return new EncourageServiceMessaging(repository, publisher);
    }
}
