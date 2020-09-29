package com.example.chatting.service;

import com.example.chatting.model.Message;

import java.time.LocalDate;
import java.util.List;

public interface MessagingService {

    void sendMessage(String sender, String message, LocalDate date);

    List<Message> getAllMessage();


}
