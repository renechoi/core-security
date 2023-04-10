package com.example.coresecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coresecurity.domain.entity.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
  int countByUsername(String username);
}