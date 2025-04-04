package com.example.server.client.controller;

import com.example.server.client.dto.request.TcpRequest;
import com.example.server.client.dto.response.TcpResponse;
import com.example.server.client.service.TcpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tcp")
@Slf4j
public class TcpController {

    @Autowired
    private TcpService tcpService;
    
    @PostMapping("/send")
    public ResponseEntity<TcpResponse> sendMessage(@RequestBody TcpRequest request) {
        log.info("Received REST request to send message: {}", request.getMessage());
        try {
            String response = tcpService.sendMessage(request.getMessage());
            return ResponseEntity.ok(new TcpResponse(response, true, null));
        } catch (Exception e) {
            log.error("Error processing TCP request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TcpResponse(null, false, e.getMessage()));
        }
    }
}
