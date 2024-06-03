package com.example.wsearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class DbUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private DbRole role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_vacancy",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vacancy_id", referencedColumnName = "id")}
    )
    private List<DbVacancy> vacancies = new ArrayList<>();

    public void addVacancy(DbVacancy vacancy) {
        this.vacancies.add(vacancy);
        vacancy.getUsers().add(this);
    }
}
