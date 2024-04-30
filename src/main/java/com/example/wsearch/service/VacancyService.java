package com.example.wsearch.service;

import com.example.wsearch.dto.VacancyDto;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.repository.RoleRepository;
import com.example.wsearch.repository.UserRepository;
import com.example.wsearch.repository.VacancyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VacancyService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private VacancyRepository vacancyRepository;
    private PasswordEncoder passwordEncoder;

    public VacancyService(UserRepository userRepository, RoleRepository roleRepository, VacancyRepository vacancyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.vacancyRepository = vacancyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addVacancy(VacancyDto vacancyDto) {
        DbVacancy vacancy = new DbVacancy();

        vacancy.setName(vacancyDto.getName());
        vacancy.setCompany(vacancyDto.getCompany());
        vacancy.setType(vacancyDto.getType());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setDate(vacancyDto.getDate());

        vacancyRepository.save(vacancy);
    }

    public void editVacancy(VacancyDto updatedVacancyDto) {
        Optional<DbVacancy> optionalVacancy = vacancyRepository.findById(updatedVacancyDto.getId());
        DbVacancy existingVacancy = optionalVacancy.orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено."));

        existingVacancy.setName(updatedVacancyDto.getName());
        existingVacancy.setCompany(updatedVacancyDto.getCompany());
        existingVacancy.setType(updatedVacancyDto.getType());
        existingVacancy.setSalary(updatedVacancyDto.getSalary());
        existingVacancy.setDescription(updatedVacancyDto.getDescription());
        existingVacancy.setDate(updatedVacancyDto.getDate());

        vacancyRepository.save(existingVacancy);
    }

    public void deleteVacancy(VacancyDto vacancyDto) {
        vacancyRepository.deleteById(vacancyDto.getId());
    }

    public DbVacancy getVacancyById(VacancyDto vacancyDto) {
        return vacancyRepository.findById(vacancyDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено."));
    }

    public List<VacancyDto> getAllVacancies() {
        List<DbVacancy> vacancies = vacancyRepository.findAll();
        return vacancies.stream()
                .map((vacancy) -> mapToVacancyDto(vacancy))
                .collect(Collectors.toList());
    }

    private VacancyDto mapToVacancyDto(DbVacancy vacancy){
        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setId(vacancy.getId());

        return vacancyDto;
    }
}
