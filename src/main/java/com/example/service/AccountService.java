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
        if ((newAccount.getUsername() == "") || (newAccount.getPassword().length() < 4)) { // if the username is empty or the length of the password is less than 4, then return null
            return null;
        }

        if (accountRepository.existsByUsername(newAccount.getUsername())) { // if the user name exists already, return exception
            throw new DuplicateUsernameException("This username already exists.");
        }

        return accountRepository.save(newAccount); // write the new account to the database
    }

    public Account loginAccount(Account account) throws AuthenticationException {
        Account storedAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()); // find the account

        if (storedAccount == null) { // if the account is not there, whether by login error or otherwise throw an exception
            throw new AuthenticationException("Invalid account");
        }
        
        storedAccount.getAccount_id(); // get the account id
        return storedAccount; // get the complete account
    }
}