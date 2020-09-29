package com.example.chatting.config;


import com.example.chatting.model.Message;
import com.example.chatting.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListeners {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListeners.class);
    private SimpMessageSendingOperations messagingTemplate;
    private final String USERNAME = "username";

    @Autowired
    public WebSocketEventListeners(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    //In the SessionDisconnect event,
    // we’ve written code to extract the user’s name
    // from the websocket session and broadcast a user leave event to all the connected clients.


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) stompHeaderAccessor.getSessionAttributes().get(USERNAME);

        if (username != null) {
            logger.info("User Disconnected : " + username);

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", message);
        }

    }


}
