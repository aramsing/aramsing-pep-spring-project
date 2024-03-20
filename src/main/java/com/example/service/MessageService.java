package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //public Message findMessageById(int message_id) {}

    //public List<Message> getAllMessagesByAccountId(int posted_by) {}

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessageById(int message_id, Message message) {
        Message updatedMessage = messageRepository.findById(message.getMessage_text()).orElseThrow(()->new ResourceNotFoundException(message + " was not found. Please try another message."));
        return messageRepository.save(updatedMessage);
    }

    //public void deleteMessageById(int message_id) {}
}
