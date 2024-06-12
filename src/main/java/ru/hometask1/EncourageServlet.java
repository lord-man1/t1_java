package ru.hometask1;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class EncourageServlet extends HttpServlet {
    private EncourageServiceImpl service;

    public EncourageServlet() {
    }

    public EncourageServlet(EncourageServiceImpl service) {
        this.service = service;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.service = new EncourageServiceImpl();
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