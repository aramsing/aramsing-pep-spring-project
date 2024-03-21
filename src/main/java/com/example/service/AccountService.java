package com.example.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account newAccount) throws DuplicateUsernameException {
        if ((newAccount.getUsername() == "") || (newAccount.getPassword().length() < 4)) {
            return null;
        }
        if (accountRepository.existsByUsername(newAccount.getUsername())) {
            throw new DuplicateUsernameException("This username already exists.");
        }
        return accountRepository.save(newAccount);
    }

    public Account loginAccount(Account account) throws AuthenticationException {
        Account storedAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(storedAccount == null) {
            throw new AuthenticationException("Invalid account");
        }
        storedAccount.getAccount_id();
        return storedAccount;
    }
}
