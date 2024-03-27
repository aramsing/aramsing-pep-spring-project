package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.entity.Account;
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

    public Message createMessage(Message message) {
        Optional<Account> id = accountRepository.findById(message.getPosted_by());

        if ((message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255) || (id.isEmpty())) {
            return null;
        }

        return messageRepository.save(message);
    }

    public void deleteMessageById(Integer message_id) throws Exception {
        if (message_id == null) {
            throw new Exception();
        }

        messageRepository.deleteById(message_id);
    }

    public List<Message> getAllMessagesByAccountId(Integer posted_by) {
        return messageRepository.findAll();
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer message_id) throws ResourceNotFoundException {
        return messageRepository.findById(message_id).orElseThrow(() -> new ResourceNotFoundException(message_id + " was not found."));
    }

    public Message updateMessageById(Integer message_id, Message message) {
        Optional<Message> id = messageRepository.findById(message_id);

        if ((message == null) || (message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255) || (id.isEmpty())) {
            return null;
        }

        return messageRepository.save(message);
    }
}