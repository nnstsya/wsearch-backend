package com.example.wsearch.controller;

import com.example.wsearch.dto.VacancyDto;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @PostMapping("jobs/create")
    public boolean createVacancy(@RequestBody VacancyDto vacancyDto) {
        return vacancyService.createVacancy(vacancyDto);
    }

    @GetMapping("jobs")
    public List<DbVacancy> getAllVacancies() {
        return vacancyService.getAllVacancies();

    }

    @DeleteMapping("jobs/{id}/edit")
    public boolean deleteVacancy(@PathVariable("id") Long id) {
        return vacancyService.deleteVacancy(id);
    }

    @GetMapping("jobs/{id}")
    public DbVacancy getVacancyById(@PathVariable("id") Long id) {
        return vacancyService.getVacancyById(id);
    }

    @PostMapping("jobs/{id}/edit")
    public boolean updateVacancy(@RequestBody VacancyDto vacancyDto, @PathVariable("student_id") Long id) {
        return vacancyService.updateVacancy(vacancyDto);
    }
}
