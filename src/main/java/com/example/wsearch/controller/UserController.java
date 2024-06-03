package com.example.wsearch.controller;

import com.example.wsearch.dto.EditUserDto;
import com.example.wsearch.dto.UserDto;
import com.example.wsearch.entity.DbUser;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}")
    public boolean editUser(@PathVariable("id") Long id, @RequestBody EditUserDto editUserDto) {
        return userService.editUser(editUserDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public DbUser getVacancyById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}