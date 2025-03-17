package com.example.server.client.connection.factory;

import com.example.server.client.config.TcpProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class TcpNetClientRetryConnectionFactory extends TcpNetClientConnectionFactory {

    private final TcpProperties tcpProperties;

    public TcpNetClientRetryConnectionFactory(TcpProperties tcpProperties) {
        super(tcpProperties.getServerHost(), tcpProperties.getServerPort());
        this.tcpProperties = tcpProperties;
    }

    @Override
    protected @NonNull Socket createSocket(@NonNull String host, int port) throws IOException {
        Socket socket = null;
        try {
            socket = super.createSocket(host, port);
            log.info("Connection established to {}:{}", host, port);
        } catch (Exception e) {
            log.warn("Connection failed, retrying in {} ms", tcpProperties.getConnectionRetryInterval());
            try {
                Thread.sleep(tcpProperties.getConnectionRetryInterval());
                socket = super.createSocket(host, port);
                log.info("Connection established after retry to {}:{}", host, port);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted during connection retry", ex);
            } catch (Exception retryEx) {
                log.error("Failed to connect after retry", retryEx);
                throw retryEx;
            }
        }
        return socket;
    }
}
