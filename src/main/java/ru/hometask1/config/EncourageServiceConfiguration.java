package ru.hometask1.config;

import ru.hometask1.EncourageRepository;
import ru.hometask1.EncourageService;
import ru.hometask1.EncourageServiceImpl;
import ru.hometask1.InMemoryEncourageRepository;

@Configuration
public class EncourageServiceConfiguration {
    @Instance
    public EncourageService encourageService() {
        EncourageRepository encourageRepository = new InMemoryEncourageRepository();
        return new EncourageServiceImpl(encourageRepository);
    }
}
