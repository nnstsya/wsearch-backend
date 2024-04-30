package com.example.wsearch.repository;

import com.example.wsearch.entity.DbVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<DbVacancy, Long> {
}