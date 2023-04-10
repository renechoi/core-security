package com.example.coresecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coresecurity.domain.entity.AccessIp;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String IpAddress);
}