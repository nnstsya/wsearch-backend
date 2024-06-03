package com.example.wsearch.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.wsearch.dto.EditUserDto;
import com.example.wsearch.dto.JwtToken;
import com.example.wsearch.dto.UserDto;
import com.example.wsearch.entity.DbUser;
import com.example.wsearch.entity.DbRole;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.security.JwtTokenProvider;
import com.example.wsearch.repository.RoleRepository;
import com.example.wsearch.repository.UserRepository;
import com.example.wsearch.repository.VacancyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public DbUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено."));
    }

    @Transactional
    public void signUp(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("User with email %s already exists".formatted(userDto.getEmail()));
        }

        DbUser user = new DbUser();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        DbRole role = roleRepository.findByName(userDto.getRole());
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }

        role.addUser(user);
        userRepository.save(user);
    }


    public DecodedJWT signIn(String email, String password) throws AccessDeniedException {
        DbUser user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AccessDeniedException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole(), user.getName());
        return jwtTokenProvider
                .toDecodedJWT(token)
                .orElseThrow(() -> new AccessDeniedException("Invalid username and/or password"));

    }

    public boolean editUser(EditUserDto editUserDto) {
        try {
            Optional<DbUser> optionalUser = userRepository.findById(editUserDto.getId());
            DbUser existingUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено."));

            existingUser.setName(editUserDto.getName());
            existingUser.setEmail(editUserDto.getEmail());

            userRepository.save(existingUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
