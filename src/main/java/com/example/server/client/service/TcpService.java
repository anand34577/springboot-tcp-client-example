package com.example.server.client.service;

import com.example.server.client.gateway.TcpClientGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TcpService {

    @Autowired
    private TcpClientGateway tcpClientGateway;
    
    public String sendMessage(String message) {
        try {
            log.info("Processing message: {}", message);
            byte[] response = tcpClientGateway.sendAndReceive(message.getBytes());
            String responseStr = new String(response);
            log.info("Received response: {}", responseStr);
            return responseStr;
        } catch (Exception e) {
            log.error("Error sending message to TCP server", e);
            throw new RuntimeException("Failed to communicate with TCP server", e);
        }
    }
}
