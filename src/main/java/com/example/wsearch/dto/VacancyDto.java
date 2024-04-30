package com.example.wsearch.dto;

import com.example.wsearch.entity.DbUser;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto
{
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Company should not be empty")
    private String company;
    @NotEmpty(message = "Type should not be empty")
    private String type;
    @NotEmpty(message = "Salary should not be empty")
    private int salary;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    @NotEmpty(message = "Date should not be empty")
    private Date date;
    private List<DbUser> users;
}