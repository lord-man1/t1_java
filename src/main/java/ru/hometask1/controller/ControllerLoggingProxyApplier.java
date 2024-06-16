package ru.hometask1.controller;

import ru.hometask1.annotation.Logged;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ControllerLoggingProxyApplier implements ProxyApplier {

    @Override
    public Object apply(Object obj) {
        var shouldBeLogged = Arrays.asList(obj.getClass().getInterfaces())
                .contains(Controller.class);
        if (shouldBeLogged) {
            return Proxy.newProxyInstance(
                    obj.getClass().getClassLoader(),
                    obj.getClass().getInterfaces(),
                    ((proxy, method, args) -> {
                        if (method.isAnnotationPresent(Logged.class)) {
                            System.out.println("Старт метода!");
                            var result = method.invoke(obj, args);
                            System.out.println("Конец метода!");
                            return result;
                        }
                        return method.invoke(obj, args);
                    })
            );
        }
        return obj;
    }
}
