package com.example.server.client.config;

import com.example.server.client.connection.factory.TcpNetClientRetryConnectionFactory;
import com.example.server.client.gateway.TcpClientGateway;
import com.example.server.client.gateway.impl.TcpClientGatewayImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayStxEtxSerializer;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@Slf4j
public class TcpClientConfig {

    @Bean
    public AbstractClientConnectionFactory clientConnectionFactory(TcpProperties tcpProperties) {
        TcpNetClientConnectionFactory factory = new TcpNetClientRetryConnectionFactory(tcpProperties);
        factory.setSerializer(new ByteArrayStxEtxSerializer());
        factory.setDeserializer(new ByteArrayStxEtxSerializer());
        factory.setSingleUse(true);
        factory.setSoTimeout(tcpProperties.getSocketTimeout());
        return factory;
    }

    @Bean
    public TcpOutboundGateway outboundGateway(AbstractClientConnectionFactory clientConnectionFactory) {
        TcpOutboundGateway gateway = new TcpOutboundGateway();
        gateway.setConnectionFactory(clientConnectionFactory);
        gateway.setRequiresReply(true);
        return gateway;
    }

    @Bean
    public DirectChannel tcpOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "tcpOutboundChannel")
    public MessageHandler tcpOutboundHandler(TcpOutboundGateway gateway) {
        return gateway;
    }

    @Bean
    public TcpClientGateway tcpClientGateway() {
        return new TcpClientGatewayImpl();
    }
}
