package com.example.wsearch.dto;

import com.example.wsearch.entity.DbRole;
import com.example.wsearch.entity.DbVacancy;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Role should not be empty")
    private String role;

    private List<DbVacancy> vacancies;

    private Long vacancyId;
}