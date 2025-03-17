package com.example.server.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tcp.client")
@Data
public class TcpProperties {
    private String serverHost = "localhost";
    private int serverPort = 8081;
    private int connectionRetryInterval = 5000;
    private int socketTimeout = 5000;
}
