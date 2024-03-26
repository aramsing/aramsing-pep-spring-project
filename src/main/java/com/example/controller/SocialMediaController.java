package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.ResourceNotFoundException;
import com.example.service.*;
import java.util.List;
import javax.security.sasl.AuthenticationException;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring.
 * The endpoints you will need can be found in readme.md as well as the test cases.
 * You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * Where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register") //DONE
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        try {
            Account newAccount = accountService.registerAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(newAccount);
        }
        
        catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("login") //DONE
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        try {
            Account loggedinAccount = accountService.loginAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(loggedinAccount);
        }
        
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("messages") //DONE
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = messageService.createMessage(message);
        if (newMessage == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(newMessage);
    }

    @GetMapping("messages") //DONE
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("messages/{message_id}") //DONE
    public ResponseEntity<Message> findMessageById(@PathVariable Integer message_id) {
        try {
            Message foundMessage = messageService.findMessageById(message_id);
            return ResponseEntity.status(HttpStatus.OK).body(foundMessage);
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @DeleteMapping("messages/{message_id}") //DONE
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
        try {
            messageService.deleteMessageById(message_id);
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }

        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping("messages/{message_id}") //DONE
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer message_id, @RequestBody Message message) {
        Message updatedMessage = messageService.updateMessageById(message_id, message);
        if (updatedMessage == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
    
    @GetMapping("accounts/{account_id}/messages") //8
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable Integer account_id) {
        List<Message> accountMessageList = messageService.getAllMessagesByAccountId(account_id);
        if (accountMessageList == null) {
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountMessageList);
    }
}
