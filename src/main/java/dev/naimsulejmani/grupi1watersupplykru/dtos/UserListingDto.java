package dev.naimsulejmani.grupi1watersupplykru.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListingDto {
    private Long id;
    private String username;


    private String email;
}
