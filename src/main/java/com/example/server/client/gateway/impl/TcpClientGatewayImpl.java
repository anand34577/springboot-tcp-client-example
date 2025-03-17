package com.example.server.client.gateway.impl;

import com.example.server.client.gateway.TcpClientGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TcpClientGatewayImpl implements TcpClientGateway {

    @Autowired
    private MessageChannel tcpOutboundChannel;

    @Override
    public byte[] sendAndReceive(byte[] message) {
        log.info("Sending message to TCP server");
        Message<byte[]> response = (Message<byte[]>) MessageBuilder
                .withPayload(message)
                .setReplyChannelName("replyChannel")
                .build();

        Message<?> replyMessage = MessagingTemplate()
                .sendAndReceive(tcpOutboundChannel, response);

        if (replyMessage != null) {
            log.info("Received response from TCP server");
            return (byte[]) replyMessage.getPayload();
        } else {
            log.error("No response received from TCP server");
            throw new RuntimeException("No response received from TCP server");
        }
    }

    private MessagingTemplate MessagingTemplate() {
        MessagingTemplate template = new MessagingTemplate();
        template.setReceiveTimeout(5000);
        return template;
    }
}
