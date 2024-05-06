package com.example.wsearch.service;

import com.example.wsearch.dto.VacancyDto;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.repository.RoleRepository;
import com.example.wsearch.repository.UserRepository;
import com.example.wsearch.repository.VacancyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacancyService {
    private VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public boolean createVacancy(VacancyDto vacancyDto) {
        DbVacancy vacancy = new DbVacancy();

        vacancy.setName(vacancyDto.getName());
        vacancy.setCompany(vacancyDto.getCompany());
        vacancy.setType(vacancyDto.getType());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setDate(vacancyDto.getDate());

        try {
            vacancyRepository.save(vacancy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateVacancy(VacancyDto updatedVacancyDto) {
        Optional<DbVacancy> optionalVacancy = vacancyRepository.findById(updatedVacancyDto.getId());
        DbVacancy existingVacancy = optionalVacancy.orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено."));

        existingVacancy.setName(updatedVacancyDto.getName());
        existingVacancy.setCompany(updatedVacancyDto.getCompany());
        existingVacancy.setType(updatedVacancyDto.getType());
        existingVacancy.setSalary(updatedVacancyDto.getSalary());
        existingVacancy.setDescription(updatedVacancyDto.getDescription());
        existingVacancy.setDate(updatedVacancyDto.getDate());

        try {
            vacancyRepository.save(existingVacancy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteVacancy(Long id) {
        try {
            vacancyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DbVacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено."));
    }

    public List<DbVacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }
}
