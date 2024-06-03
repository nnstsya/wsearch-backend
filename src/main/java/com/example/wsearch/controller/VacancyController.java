package com.example.wsearch.controller;

import com.example.wsearch.dto.UserDto;
import com.example.wsearch.dto.VacancyDto;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jobs")
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/create")
    public boolean createVacancy(@RequestBody VacancyDto vacancyDto) {
        return vacancyService.createVacancy(vacancyDto);
    }

    @GetMapping("")
    public List<DbVacancy> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    @DeleteMapping("/{id}")
    public boolean deleteVacancy(@PathVariable("id") Long id) {
        return vacancyService.deleteVacancy(id);
    }

    @GetMapping("/{id}")
    public DbVacancy getVacancyById(@PathVariable("id") Long id) {
        return vacancyService.getVacancyById(id);
    }

    @PutMapping("/{id}/edit")
    public boolean updateVacancy(@RequestBody VacancyDto vacancyDto, @PathVariable("id") Long id) {
        return vacancyService.updateVacancy(vacancyDto, id);
    }

    @PutMapping("/{id}")
    public boolean addVacancy(@RequestBody Long userId, @PathVariable("id") Long vacancyId) {
        return vacancyService.addVacancy(userId, vacancyId);
    }
}
