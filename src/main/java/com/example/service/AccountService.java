package com.example.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account newAccount) {
        if ((newAccount.getUsername() == "") || (newAccount.getPassword().length() < 4)) {
            return null;
        }
        return accountRepository.save(newAccount);
    }

    public Account loginAccount(Account account) throws AuthenticationException {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
