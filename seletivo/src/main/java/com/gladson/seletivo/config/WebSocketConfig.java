package com.gladson.seletivo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefixo do broker que envia mensagens para o front
        registry.enableSimpleBroker("/topic");
        // Prefixo usado para enviar mensagens do front para o back
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint para o front se conectar via WebSocket
        registry.addEndpoint("/ws-albums")
                .setAllowedOriginPatterns("*") // ou restrinja ao domínio do front
                .withSockJS(); // fallback para navegadores antigos
    }
}
