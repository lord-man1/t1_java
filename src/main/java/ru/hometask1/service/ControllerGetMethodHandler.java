package ru.hometask1.service;

import ru.hometask1.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ControllerGetMethodHandler implements ControllerMethodHandler {
    private static final String METHOD_GET = "GET";

    @Override
    public Optional<Method> findMethod(Class<?> clazz, String uri) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> uri.contains(method.getAnnotation(GetMapping.class).value()))
                .findFirst();
    }

    @Override
    public String getMethodType() {
        return METHOD_GET;
    }
}
