package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import com.example.exception.DuplicateUsernameException;
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
    public ResponseEntity<Account> registerAccount(@RequestBody Account newAccount) {
        try {
            Account registeredAccount = accountService.registerAccount(newAccount);
            return ResponseEntity.status(HttpStatus.OK).body(registeredAccount);
        }
        
        catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("login") //DONE
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) throws AuthenticationException {
        try {
            Account loggedinAccount = accountService.loginAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(loggedinAccount);
        }
        
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("messages") //3
    public ResponseEntity<Message> createMessage(@RequestBody Message newMessage) {
        Message message = messageService.createMessage(newMessage);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("messages") //DONE
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("messages/{message_id}") //5
    public ResponseEntity<Message> findMessageById(@RequestBody int message_id) {
        Message message = messageService.findMessageById(message_id);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("messages/{message_id}") //6
    public ResponseEntity<Message> deleteMessageById(@RequestBody int message_id) {
        return null;
    }

    @PatchMapping("messages/{message_id}") //7
    public ResponseEntity<Message> updateMessageById(@RequestBody int message_id, @RequestBody Message message) {
        messageService.updateMessageById(message_id, message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    
    @GetMapping("accounts/{account_id}/messages") //8
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@RequestBody int account_id) {
        return null;
    }
}
