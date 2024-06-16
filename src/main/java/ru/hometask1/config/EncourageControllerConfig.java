package ru.hometask1.config;

import ru.hometask1.annotation.Configuration;
import ru.hometask1.annotation.Instance;
import ru.hometask1.controller.Controller;
import ru.hometask1.controller.ControllerLoggingProxyApplier;
import ru.hometask1.controller.ProxyApplier;
import ru.hometask1.service.EncourageService;
import ru.hometask1.controller.EncourageController;
import ru.hometask1.service.EncourageServiceImpl;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class EncourageControllerConfig {
    @Instance(priority = Integer.MAX_VALUE)
    public ProxyApplier controllerLoggingProxyApplier() {
        return new ControllerLoggingProxyApplier();
    }

    @Instance
    public Controller encourageController(EncourageService encourageService) {
        return new EncourageController(encourageService);
    }
}
