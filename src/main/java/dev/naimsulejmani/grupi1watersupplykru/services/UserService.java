package dev.naimsulejmani.grupi1watersupplykru.services;


import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;

public interface UserService {
    UserDto login(String email, String password);
}
