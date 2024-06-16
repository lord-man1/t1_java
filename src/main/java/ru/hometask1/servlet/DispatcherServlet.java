package ru.hometask1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.hometask1.ApplicationContext;
import ru.hometask1.ApplicationContextHolder;
import ru.hometask1.service.ControllerMethodProvider;
import ru.hometask1.dto.ExampleRequest;

public class DispatcherServlet extends HttpServlet {
    private ApplicationContext ctx;
    private ControllerMethodProvider provider;

    public DispatcherServlet() {
        init();
    }

    @Override
    public void init() {
        this.ctx = ApplicationContextHolder.getInstance();
        this.provider = ctx.getInstance(ControllerMethodProvider.class);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var method = provider.findControllerMethod(req);

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object response = method.invoke(ctx.getInstance(method.getDeclaringClass()));
            resp.setContentType("application/json");
            resp.getWriter().println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        var method = provider.findControllerMethod(req);

        try {
            StringBuilder builder = new StringBuilder();
            var reader = req.getReader();
            reader.lines().forEach(builder::append);

            ObjectMapper mapper = new ObjectMapper();
            method.invoke(ctx.getInstance(method.getDeclaringClass()), mapper.readValue(builder.toString(), ExampleRequest.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
