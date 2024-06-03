package com.example.wsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vacancy")
@JsonIgnoreProperties({"users"})
public class DbVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String company;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private int salary;

    @Column(nullable=false, length = 4000)
    private String description;

    @Column(nullable=false)
    private Date date;

    @ManyToMany(mappedBy="vacancies")
    private List<DbUser> users = new ArrayList<>();
}
