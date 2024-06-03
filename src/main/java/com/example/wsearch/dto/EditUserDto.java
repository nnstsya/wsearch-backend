package com.example.wsearch.dto;

import com.example.wsearch.entity.DbVacancy;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto
{
    private Long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
}