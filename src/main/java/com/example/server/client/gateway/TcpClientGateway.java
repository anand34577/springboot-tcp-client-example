package com.example.server.client.gateway;

public interface TcpClientGateway {
    byte[] sendAndReceive(byte[] message);
}
