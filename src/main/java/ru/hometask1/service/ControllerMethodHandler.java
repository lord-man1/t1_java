package ru.hometask1.service;

import java.lang.reflect.Method;
import java.util.Optional;

public interface ControllerMethodHandler {
    Optional<Method> findMethod(Class<?> clazz, String uri);

    String getMethodType();
}
