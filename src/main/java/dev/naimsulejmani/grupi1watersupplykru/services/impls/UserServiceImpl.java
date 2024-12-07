package dev.naimsulejmani.grupi1watersupplykru.services.impls;

import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.mappers.UserMapper;
import dev.naimsulejmani.grupi1watersupplykru.models.User;
import dev.naimsulejmani.grupi1watersupplykru.repositories.UserRepository;
import dev.naimsulejmani.grupi1watersupplykru.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Password incorrect");
            return null;
        }

        return userMapper.toDto(user);
    }
}










