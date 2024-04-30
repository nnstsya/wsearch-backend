package com.example.wsearch.dto;

import com.example.wsearch.entity.DbRole;
import com.example.wsearch.entity.DbVacancy;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Email should not be empty")
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Role should not be empty")
    private Long roleId;
    private List<DbVacancy> vacancies;
    private Long vacancyId;
}