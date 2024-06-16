package ru.hometask1.service;

import jakarta.servlet.http.HttpServletRequest;
import org.reflections.Reflections;
import ru.hometask1.controller.Controller;

import java.lang.reflect.Method;
import java.util.Set;

public class ControllerMethodProvider {
    private ControllerMethodHandlerProvider provider;

    public ControllerMethodProvider(ControllerMethodHandlerProvider provider) {
        this.provider = provider;
    }

    public Method findControllerMethod(HttpServletRequest req) {
        for (var controller : findControllers()) {
            var methodHandler = provider.getHandler(req.getMethod());
            var method = methodHandler.findMethod(controller, req.getRequestURI());
            if (method.isPresent()) {
                return method.get();
            }
        }
        throw new RuntimeException("Method not found");
    }

    private Set<Class<? extends Controller>> findControllers() {
        var reflections = new Reflections("ru.hometask1.controller");
        return reflections.getSubTypesOf(Controller.class);
    }

    public void setProvider(ControllerMethodHandlerProvider provider) {
        this.provider = provider;
    }
}
