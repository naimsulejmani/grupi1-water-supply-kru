package dev.naimsulejmani.grupi1watersupplykru.services.impls;

import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.mappers.UserMapperImpl;
import dev.naimsulejmani.grupi1watersupplykru.models.User;
import dev.naimsulejmani.grupi1watersupplykru.repositories.UserRepository;
import dev.naimsulejmani.grupi1watersupplykru.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapperImpl userMapperImpl;

    public UserServiceImpl(UserRepository repository, UserMapperImpl userMapperImpl) {
        this.repository = repository;
        this.userMapperImpl = userMapperImpl;

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
}










