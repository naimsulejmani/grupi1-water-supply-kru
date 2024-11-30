package dev.naimsulejmani.grupi1watersupplykru.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customers")
//@Table(name = "customers", schema = "HR")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 500)
    private String companyName;
    @Column(nullable = false, length = 500)
    private String address;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 100)
    private String state;
    @Column(nullable = false, length = 10)
    private String zip;
    @Column(length = 50)
    private String email;
    @Column(length = 25)
    private String phone;
    @Column(nullable = false)
    private LocalDate registeredDate;
    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, length = 50, updatable = false)
    private String createdBy = "admin";
    private LocalDateTime modifiedAt;
    @Column(length = 50)
    private String modifiedBy;


}
