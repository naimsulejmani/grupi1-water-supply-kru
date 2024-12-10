package dev.naimsulejmani.grupi1watersupplykru.mappers;

import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.dtos.UserListingDto;
import dev.naimsulejmani.grupi1watersupplykru.infrastructure.Convert;
import dev.naimsulejmani.grupi1watersupplykru.models.User;

public interface UserMapper extends Convert<UserDto, User> {

    UserListingDto toUserListingDto(User user);
}
