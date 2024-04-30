package com.example.wsearch.repository;

import com.example.wsearch.entity.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DbUser, Long> {
}