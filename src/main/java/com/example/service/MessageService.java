package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.*;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
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

    public Message createMessage(Message message) throws ResourceNotFoundException {
        if ((message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255)) {
            return null;
        }

        //Optional<Message> optionalPostedByUser = accountRepository;
        return messageRepository.save(message);
    }

    public Message updateMessageById(int message_id, Message message) {
        return messageRepository.save(message);
    }

    public Message deleteMessageById(Message message) {
        return null;
    }
}
