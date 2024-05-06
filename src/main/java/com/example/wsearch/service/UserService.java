package com.example.wsearch.service;

import com.example.wsearch.dto.UserDto;
import com.example.wsearch.entity.DbUser;
import com.example.wsearch.entity.DbRole;
import com.example.wsearch.entity.DbVacancy;
import com.example.wsearch.repository.RoleRepository;
import com.example.wsearch.repository.UserRepository;
import com.example.wsearch.repository.VacancyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private VacancyRepository vacancyRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, VacancyRepository vacancyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.vacancyRepository = vacancyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto) {
        DbUser user = new DbUser();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        DbRole role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new IllegalArgumentException("Роль не знайдено."));
        user.setRole(role);

        userRepository.save(user);
    }

    public void editUser(UserDto updatedUserDto) {
        Optional<DbUser> optionalUser = userRepository.findById(updatedUserDto.getId());
        DbUser existingUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено."));

        String name = updatedUserDto.getFirstName() + updatedUserDto.getLastName();

        existingUser.setName(name);
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setPassword(updatedUserDto.getPassword());

        userRepository.save(existingUser);
    }

    public void deleteUser(UserDto updatedUserDto) {
        userRepository.deleteById(updatedUserDto.getId());
    }

    public void addVacancyResponse(UserDto updatedUserDto) {
        Optional<DbUser> optionalUser = userRepository.findById(updatedUserDto.getId());
        DbUser existingUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено."));

        DbVacancy vacancy = vacancyRepository.findById(updatedUserDto.getVacancyId()).orElseThrow(() -> new IllegalArgumentException("Вакансію не знайдено."));

        existingUser.setVacancies(Arrays.asList(vacancy));

        userRepository.save(existingUser);
    }

    public DbUser getUserById(UserDto userDto) {
        return userRepository.findById(userDto.getId()).orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено."));
    }

    public List<UserDto> getAllUsers() {
        List<DbUser> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(DbUser user){
        UserDto userDto = new UserDto();

        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setId(user.getId());

        return userDto;
    }
}
