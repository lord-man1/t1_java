package ru.hometask1;

import org.reflections.Reflections;
import ru.hometask1.config.Configuration;
import ru.hometask1.config.Instance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;

public class ApplicationContext {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public ApplicationContext() throws InvocationTargetException, IllegalAccessException {
        Reflections reflections = new Reflections("ru.hometask1");
        var configTypes = reflections.getTypesAnnotatedWith(Configuration.class);
        var configInstances = configTypes.stream().map(type -> {
            try {
                return type.getConstructor().newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        for (var configInstance : configInstances) {
            var methods = stream(configInstance.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(Instance.class))
                    .toList();
            for (var method : methods) {
                instances.put(method.getReturnType(), method.invoke(configInstance));
            }
        }
    }

    public <T> T getInstance(Class<T> clazz) {
        return (T) instances.get(clazz);
    }
}
