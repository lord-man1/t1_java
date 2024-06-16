package ru.hometask1.config;

import ru.hometask1.annotation.Configuration;
import ru.hometask1.annotation.Instance;
import ru.hometask1.service.*;

import java.util.Map;

@Configuration
public class ControllerMethodProviderConfiguration {
    @Instance
    public ControllerMethodProvider controllerMethodProvider(ControllerMethodHandlerProvider provider) {
        return new ControllerMethodProvider(provider);
    }

    @Instance
    public ControllerMethodHandlerProvider controllerMethodHandlerProvider(ControllerMethodHandler... methodHandlers) {
        return new ControllerMethodHandlerProvider(methodHandlers);
    }

    @Instance
    public ControllerMethodHandler[] methodHandlers() {
        return new ControllerMethodHandler[]{
                new ControllerGetMethodHandler(), new ControllerPostMethodHandler()
        };
    }
}
