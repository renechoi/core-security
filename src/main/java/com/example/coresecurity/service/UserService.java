package com.example.coresecurity.service;


import java.util.List;

import com.example.coresecurity.domain.dto.AccountDto;
import com.example.coresecurity.domain.entity.Account;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
