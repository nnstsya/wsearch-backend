package com.example.wsearch.repository;

import com.example.wsearch.entity.DbVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<DbVacancy, Long> {
}