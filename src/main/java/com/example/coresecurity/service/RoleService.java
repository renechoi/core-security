package com.example.coresecurity.service;


import java.util.List;

import com.example.coresecurity.domain.entity.Role;

public interface RoleService {

    Role getRole(long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}