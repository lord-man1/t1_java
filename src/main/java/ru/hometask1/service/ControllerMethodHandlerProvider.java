package ru.hometask1.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ControllerMethodHandlerProvider {
    private Map<String, ControllerMethodHandler> handlers;

    public ControllerMethodHandlerProvider(ControllerMethodHandler[] methodHandlers) {
        this.handlers = new HashMap<>();
        for (var handler : methodHandlers) {
            handlers.put(handler.getMethodType(), handler);
        }
    }

    public ControllerMethodHandler getHandler(String methodType) {
        return Optional.ofNullable(handlers.get(methodType)).orElseThrow(
                () -> new RuntimeException("No handler found for " + methodType)
        );
    }

    public void setHandlers(Map<String, ControllerMethodHandler> handlers) {
        this.handlers = handlers;
    }
}
