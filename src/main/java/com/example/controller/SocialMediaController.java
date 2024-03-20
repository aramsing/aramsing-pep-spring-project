package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
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

    @PostMapping("register") //1
    public ResponseEntity<Account> registerAccount(@RequestBody Account newAccount) {
        accountService.registerAccount(newAccount);
        if (newAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(newAccount);
    }

    @PostMapping("login") //2
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) throws AuthenticationException {
        Account loggedinAccount = accountService.loginAccount(account);
        if (loggedinAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
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
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("messages/{message_id}") //5
    public ResponseEntity<Message> findMessageById(@RequestBody int message_id) {
        return null;
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
