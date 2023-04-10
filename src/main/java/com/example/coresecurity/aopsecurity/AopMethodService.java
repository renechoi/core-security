package com.example.coresecurity.aopsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AopMethodService {

    public void methodSecured() {

        System.out.println("methodSecured");
    }
}