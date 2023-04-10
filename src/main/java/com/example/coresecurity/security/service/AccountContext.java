package com.example.coresecurity.security.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.coresecurity.domain.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountContext extends User {
  private final Account account;

  public AccountContext(Account account, ArrayList<GrantedAuthority> roles) {
    super(account.getUsername(), account.getPassword(), roles);
    this.account = account;
  }

}
