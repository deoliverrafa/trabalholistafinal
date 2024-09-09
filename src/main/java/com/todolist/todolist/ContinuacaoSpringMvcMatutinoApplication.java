package com.todolist.todolist;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class ContinuacaoSpringMvcMatutinoApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ContinuacaoSpringMvcMatutinoApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute("titulo","Meu Site com SpringMVC");
    }
}
