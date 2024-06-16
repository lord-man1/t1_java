package ru.hometask1.service;

import ru.hometask1.annotation.PostMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ControllerPostMethodHandler implements ControllerMethodHandler {
    private static final String METHOD_POST = "POST";

    @Override
    public Optional<Method> findMethod(Class<?> clazz, String uri) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(PostMapping.class))
                .filter(method -> uri.contains(method.getAnnotation(PostMapping.class).value()))
                .findFirst();
    }

    @Override
    public String getMethodType() {
        return METHOD_POST;
    }
}
