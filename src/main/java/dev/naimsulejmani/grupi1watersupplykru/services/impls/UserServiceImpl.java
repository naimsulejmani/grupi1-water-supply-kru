package dev.naimsulejmani.grupi1watersupplykru.services.impls;

import dev.naimsulejmani.grupi1watersupplykru.dtos.RegisterUserRequestDto;
import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.exceptions.EmailExistException;
import dev.naimsulejmani.grupi1watersupplykru.exceptions.UsernameExistException;
import dev.naimsulejmani.grupi1watersupplykru.mappers.UserMapperImpl;
import dev.naimsulejmani.grupi1watersupplykru.models.User;
import dev.naimsulejmani.grupi1watersupplykru.repositories.UserRepository;
import dev.naimsulejmani.grupi1watersupplykru.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapperImpl userMapperImpl;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapperImpl userMapperImpl, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapperImpl = userMapperImpl;
        this.passwordEncoder = passwordEncoder;

        if (repository.count() == 0) {
            User user = new User();
            user.setEmail("naim.sulejmani@gmail.com");
            user.setActive(true);
            user.setName("Naim");
            user.setSurname("Sulejmani");
            user.setBirthdate(LocalDate.parse("1986-12-16"));
            user.setRole("ROLE_ADMIN");
            user.setPhone("049111111");
            user.setPassword("Admin123");
            user.setUsername("naimsulejmani");

            repository.save(user);

        }
    }

    @Override
    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("User not found");
            return null; // throw new InvalidCredentialsException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Password incorrect");
            return null; // throw new InvalidCredentialsException("Wrong password");
        }

        return userMapperImpl.toDto(user);
    }

    @Override
    public boolean register(RegisterUserRequestDto registerUserRequestDto) {
        if (repository.findByUsername(registerUserRequestDto.getUsername()).isPresent()) {
            throw new UsernameExistException();
        }
        if (repository.findByEmail(registerUserRequestDto.getEmail()).isPresent()) {
            throw new EmailExistException();
        }

        User user = userMapperImpl.userRequestDtoToUser(registerUserRequestDto);
        user.setActive(true);

        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));

        repository.save(user);

        return true;
    }
}










