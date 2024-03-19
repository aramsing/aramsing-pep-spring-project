package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private MessageService messageService;

    @Autowired
    public AccountService(AccountRepository accountRepository, MessageService messageService) {
        this.accountRepository = accountRepository;
        this.messageService = messageService;
    }

    public void registerAccount(Account newAccount) {
        accountRepository.save(newAccount);
    }

    public void loginAccount(String username, String password) {
        accountRepository.findByUsernameAndPassword(username, password);
    }
}
