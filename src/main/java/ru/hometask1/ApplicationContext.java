package ru.hometask1;

import org.reflections.Reflections;
import ru.hometask1.annotation.Configuration;
import ru.hometask1.annotation.Instance;
import ru.hometask1.controller.ProxyApplier;

import java.lang.reflect.Method;
import java.util.*;

public class ApplicationContext {
    private final Map<String, Object> instances = new HashMap<>();
    private final Set<String> instancesInProgress = new HashSet<>();
    private final Map<Class<?>, Object> configClasses = new HashMap<>();
    private final Set<Method> instanceMethods = new HashSet<>();

    public ApplicationContext(String packageName) {
        scanConfigurationClasses(packageName);
        init();
    }

    public void scanConfigurationClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        var configurationClasses = reflections.getTypesAnnotatedWith(Configuration.class);
        for (var configClass : configurationClasses) {
            try {
                var configInstance = configClass.getConstructor().newInstance();
                configClasses.put(configClass, configInstance);
                for (var method : configClass.getMethods()) {
                    if (method.isAnnotationPresent(Instance.class)) {
                        instanceMethods.add(method);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void init() {
        instanceMethods.stream()
                .sorted(Comparator.comparingInt((Method method) ->
                        method.getAnnotation(Instance.class).priority()).reversed())
                .forEach(method -> createInstance(method.getName(), method));
    }

    public Object createInstance(String methodName, Method method) {
        if (instancesInProgress.contains(methodName)) {
            throw new RuntimeException("Circular dependency found: " + methodName);
        }
        instancesInProgress.add(methodName);

        try {
            Object configInstance = configClasses.get(method.getDeclaringClass());
            Object[] dependencies = resolveDependencies(method);
            var instance = method.invoke(configInstance, dependencies);
            instances.put(methodName, applyProxy(instance));
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            instancesInProgress.remove(methodName);
        }
    }

    public Object[] resolveDependencies(Method method) {
        return Arrays.stream(method.getParameterTypes())
                .map(this::getInstance)
                .toArray();
    }

    public Object applyProxy(Object instanceToProxy) {
        for (var instance : getInstances(ProxyApplier.class)) {
            return instance.apply(instanceToProxy);
        }
        return instanceToProxy;
    }

    public <T> List<T> getInstances(Class<T> instanceType) {
        return (List<T>) instances.values().stream()
                .filter(instanceType::isInstance)
                .toList();
    }

    public <T> T getInstance(Class<T> instanceType) {
        return instanceType.cast(instances.values().stream()
                .filter(instanceType::isInstance)
                .findFirst()
                .orElseGet(() -> createInstanceByType(instanceType)));
    }

    public Object createInstanceByType(Class<?> instanceType) {
        for (var instanceMethod : instanceMethods) {
            if (instanceMethod.getReturnType().isAssignableFrom(instanceType)) {
                return createInstance(instanceMethod.getName(), instanceMethod);
            }
        }
        throw new RuntimeException("No instance method found: " + instanceType);
    }
}
