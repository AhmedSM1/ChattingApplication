package com.example.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //STOMP stands for simple Text Oriented Messaging Protocol

    //SockJS is a JavaScript library (for browsers) that provides a WebSocket-like object. SockJS gives you a coherent, cross-browser,
    // Javascript API which creates a low latency, full duplex, cross-domain communication channel between the browser and the web server
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint that the clients will use to connect to our websocket server.
        registry.addEndpoint("/messages").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }


}
//Why do we need STOMP? Well, WebSocket is just a communication protocol.
//        It doesn’t define things like - How to send a message only to users
//        who are subscribed to a particular topic, or how to send a message to a particular user. We need STOMP for these functionalities.