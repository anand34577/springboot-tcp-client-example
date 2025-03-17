package com.example.server.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TcpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcpClientApplication.class, args);
    }

}
