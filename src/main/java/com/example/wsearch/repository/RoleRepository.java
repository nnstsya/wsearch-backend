package com.example.wsearch.repository;

import com.example.wsearch.entity.DbRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<DbRole, Long> {
    DbRole findByName(String name);
}