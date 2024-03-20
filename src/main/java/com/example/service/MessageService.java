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

    public Message findMessageById(int message_id) {
        return null;
    }

    public List<Message> getAllMessagesByAccountId(int posted_by) {
        return null;
    }

    public Message createMessage(Message message) {
        if ((message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255) || (message.getPosted_by() == null)) {
            return null;
        }
        return messageRepository.save(message);
    }

    public Message updateMessageById(int message_id, Message message) throws ResourceNotFoundException {
        Message updatedMessage = messageRepository.findById(message.getMessage_text()).orElseThrow(()->new ResourceNotFoundException("message not found"));
        return messageRepository.save(updatedMessage);
    }

    public Message deleteMessageById(Message message) {
        return null;
    }
}
