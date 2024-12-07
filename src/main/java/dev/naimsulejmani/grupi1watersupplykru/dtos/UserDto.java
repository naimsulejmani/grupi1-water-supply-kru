package dev.naimsulejmani.grupi1watersupplykru.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String role;
    private String phone;
    private LocalDate birthdate;
    private boolean active;

    public String getFullName() {
        return name + " " + surname;
    }
}
