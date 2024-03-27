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
        Optional<Account> id = accountRepository.findById(message.getPosted_by()); // gets the account

        if ((message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255) || (id.isEmpty())) { // if the message text is empty or more than 255 characters or if the account id is empty, then return null
            return null;
        }

        return messageRepository.save(message); // write the message to the database
    }

    public void deleteMessageById(Integer message_id) throws Exception {
        if (message_id == null) { // if the message id is null, then throw a new exception
            throw new Exception();
        }

        messageRepository.deleteById(message_id); // delete the message from the database
    }

    public List<Message> getAllMessagesByAccountId(List<Integer> account_id) {
        return messageRepository.findAllById(account_id); // gets all the messages with a specific account id, we have it as a list because we should be having a few messages
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll(); // gets all the messages
    }

    public Message getMessageById(Integer message_id) throws ResourceNotFoundException {
        return messageRepository.findById(message_id).orElseThrow(() -> new ResourceNotFoundException(message_id + " was not found.")); // gets the individual message
    }

    public Message updateMessageById(Integer message_id, Message message) {
        Optional<Message> id = messageRepository.findById(message_id); // gets the message 

        if ((message == null) || (message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255) || (id.isEmpty())) { // if the message is null or the message text is empty or more than 255 characters or if the account id is empty, then return null
            return null;
        }

        return messageRepository.save(message); // write the new message text to the database
    }
}