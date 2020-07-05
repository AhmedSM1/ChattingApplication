package com.example.chatting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Configuration
@EnableAutoConfiguration
@Import(WebSocketConfig.class)
@EnableWebSocketMessageBroker
@EnableScheduling
public class ChattingAppConfig {

    private final SimpMessageSendingOperations messagingTemplate;


    @Autowired
    public ChattingAppConfig(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    
}
