package ru.hometask1.config;

import ru.hometask1.annotation.Configuration;
import ru.hometask1.annotation.Instance;
import ru.hometask1.repository.EncourageRepository;
import ru.hometask1.service.EncourageService;
import ru.hometask1.service.EncourageServiceImpl;
import ru.hometask1.repository.InMemoryEncourageRepository;

@Configuration
public class EncourageServiceConfiguration {
    @Instance
    public EncourageService encourageService(EncourageRepository encourageRepository) {
        return new EncourageServiceImpl(encourageRepository);
    }

    @Instance
    public EncourageRepository encourageRepository() {
        return new InMemoryEncourageRepository();
    }
}
