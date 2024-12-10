package dev.naimsulejmani.grupi1watersupplykru.mappers;

import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.dtos.UserListingDto;
import dev.naimsulejmani.grupi1watersupplykru.infrastructure.Convert;
import dev.naimsulejmani.grupi1watersupplykru.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setRole(user.getRole());
        userDto.setPhone(user.getPhone());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setActive(user.isActive());
        return userDto;
    }


    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRole(userDto.getRole());
        user.setPhone(userDto.getPhone());
        user.setBirthdate(userDto.getBirthdate());
        user.setActive(userDto.isActive());
        return user;
    }

    @Override
    public UserListingDto toUserListingDto(User user) {
        UserListingDto dto = new UserListingDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
