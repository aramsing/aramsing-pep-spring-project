package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import com.example.service.*;
import java.util.List;

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

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register") //1
    public ResponseEntity<String> registerAccount(@RequestBody Account newAccount) {
        return null;
    }

    @PostMapping("login") //2
    public ResponseEntity<Void> loginAccount(@RequestBody Account account) {
        return null;
    }

    @PostMapping("messages") //3
    public ResponseEntity<Message> createMessage(@RequestBody Message newMessage) {
        return null;
    }

    @GetMapping("messages") //4
    public ResponseEntity<List<Message>> getAllMessages() {
        return null;
    }

    @GetMapping("messages/{message_id}") //5
    public ResponseEntity<Message> findMessageById(@PathVariable int message_id) {
        return null;
    }

    @DeleteMapping("messages/{message_id}") //6
    public ResponseEntity<Message> deleteMessageById(@PathVariable int message_id) {
        return null;
    }

    @PatchMapping("messages/{message_id}") //7
    public ResponseEntity<Message> updateMessageById(@PathVariable int message_id) {
        return null;
    }
    
    @GetMapping("accounts/{account_id}/messages") //8
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int account_id) {
        return null;
    }
}
