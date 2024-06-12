package ru.hometask1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class EncourageServlet extends HttpServlet {
    private final ApplicationContext ctx = new ApplicationContext();
    private EncourageServiceImpl service;

    public EncourageServlet() throws ServletException, InvocationTargetException, IllegalAccessException {
        init();
    }

    public EncourageServlet(EncourageServiceImpl service) throws InvocationTargetException, IllegalAccessException {
        this.service = service;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = ctx.getInstance(EncourageServiceImpl.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(service.getRandomPhrase());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader in = req.getReader();
        service.addPhrase(in.readLine());
    }
}