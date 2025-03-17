package com.example.server.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcpResponse {
    private String response;
    private boolean success;
    private String errorMessage;
}