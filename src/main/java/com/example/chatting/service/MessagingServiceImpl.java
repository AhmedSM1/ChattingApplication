package com.example.chatting.service;

import com.example.chatting.model.Message;
import com.example.chatting.model.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessagingServiceImpl implements MessagingService {

    private MessageRepository repository;

    @Autowired
    public MessagingServiceImpl(MessageRepository messageRepository) {
        this.repository = messageRepository;
    }

    @Override
    public void sendMessage(String sender, String message, LocalDate date) {

    }

    @Override
    public List<Message> getAllMessage() {
        return repository.findAll();
    }
}

