package com.example.securedigitalticketingsystemmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

public class SecureDigitalTicketingSystemMainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecureDigitalTicketingSystemMainApplication.class, args);
        String port = run.getEnvironment().getProperty("server.port");
        System.out.println("http://localhost:"+port+"/login.html");
    }
}