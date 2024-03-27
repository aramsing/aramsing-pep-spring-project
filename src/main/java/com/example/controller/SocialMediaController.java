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
            Account newAccount = accountService.registerAccount(account); // create the account
            return ResponseEntity.status(HttpStatus.OK).body(newAccount); // return 200
        }
        
        catch (DuplicateUsernameException e) { // if the program catch a duplicate username
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // return 409
        }
    }

    @PostMapping("login") //DONE
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        try {
            Account loggedinAccount = accountService.loginAccount(account); // log into the account
            return ResponseEntity.status(HttpStatus.OK).body(loggedinAccount); // return 200
        }
        
        catch (AuthenticationException e) { // if the user entered the wrong credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // return 401
        }
    }

    @PostMapping("messages") //DONE
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = messageService.createMessage(message); // create the message

        if (newMessage == null) { // if the message is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // return 400
        }

        return ResponseEntity.status(HttpStatus.OK).body(newMessage); // return 200
    }

    @GetMapping("messages") //DONE
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages(); // get every message currently in the database
        return ResponseEntity.status(HttpStatus.OK).body(messages); // return 200
    }

    @GetMapping("messages/{message_id}") //DONE
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id) {
        try {
            Message foundMessage = messageService.getMessageById(message_id); // get the individual message from the database
            return ResponseEntity.status(HttpStatus.OK).body(foundMessage); // return 200
        }

        catch (ResourceNotFoundException e) { // if we are not able to find the resource
            return ResponseEntity.status(HttpStatus.OK).body(null); // return 200
        }
    }

    @DeleteMapping("messages/{message_id}") //DONE
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
        try {
            messageService.deleteMessageById(message_id); // delete the message from the database
            return ResponseEntity.status(HttpStatus.OK).body(1); // return 200
        }

        catch (Exception e) { // if the message does not exist
            return ResponseEntity.status(HttpStatus.OK).body(null); // return 200
        }
    }

    @PatchMapping("messages/{message_id}") //DONE
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer message_id, @RequestBody Message message) {
        Message updatedMessage = messageService.updateMessageById(message_id, message); // update the message in the database

        if (updatedMessage == null) { // if the message does not exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // return 400
        }

        return ResponseEntity.status(HttpStatus.OK).body(1); // return 200
    }
    
    @GetMapping("accounts/{account_id}/messages") //DONE
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable List<Integer> account_id) {
        List<Message> messagesFromAccount = messageService.getAllMessagesByAccountId(account_id); // get all messages written by a user from the database
        return ResponseEntity.status(HttpStatus.OK).body(messagesFromAccount); // return 200
    }
}