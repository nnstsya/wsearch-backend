package com.example.wsearch.repository;

import com.example.wsearch.entity.DbRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<DbRole, Long> {
}