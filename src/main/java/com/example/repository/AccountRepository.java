package com.example.repository;

import com.example.entity.Account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
