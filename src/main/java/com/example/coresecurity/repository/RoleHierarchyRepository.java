package com.example.coresecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coresecurity.domain.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}