package com.example.chatting.controller;

import com.example.chatting.model.Message;
import com.example.chatting.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class MessageController {


    private MessagingService messagingService;
    private final String USERNAME = "username";

    @Autowired
    public MessageController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String indexPage(){
        return "index";
    }

//
//    @PostMapping("/sendMessage")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String sendMessage(@RequestParam String sender,
//                              @RequestParam String message,
//                              Model model){
//
//        model.addAttribute("sender",sender);
//        model.addAttribute("message",message);
//        model.addAttribute("date sent", LocalDate.now().getDayOfMonth());
//        return "sendMessage";
//    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {

        //get the user and then add to websocket session
        headerAccessor.getSessionAttributes().put(USERNAME, message.getSender());
        return message;
    }


}
