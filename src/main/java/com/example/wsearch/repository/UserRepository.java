package com.example.wsearch.repository;

import com.example.wsearch.entity.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Long> {
    Optional<DbUser> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<DbUser> findByEmailAndPassword(String email, String password);
}