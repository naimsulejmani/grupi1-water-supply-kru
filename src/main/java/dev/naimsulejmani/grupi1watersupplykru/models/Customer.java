package dev.naimsulejmani.grupi1watersupplykru.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
//    @Min(value = 0, message = "ID duhet te jete numer pozitiv ose zero!")
    @PositiveOrZero(message = "ID duhet te jete numer pozitiv ose zero!")
    private long id;

    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "Emri duhet te jete me shkruajt 3 deri ne 50 karaktere!")
    @NotBlank(message = "Emri nuk mund te jete i zbrazet!")
    @NotNull(message = "Emri nuk mund te jete null!")
//    @NotBlank
//    @NotEmpty
//    @NotNull
    private String firstName;
    @Column(nullable = false, length = 50)
    @Size(max = 50)
    @NotBlank
    private String lastName;

    @Column(length = 500)
    @Size(max = 500)
    private String companyName;

    @Column(nullable = false, length = 500)
    @Size(max = 500)
    private String address;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "Qyteti duhet te jete me shkruajt deri ne 100 karaktere!")
    private String city;

    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "Shteti duhet te jete me shkruajt deri ne 100 karaktere!")
    //@NotEqual(value = "Serbia", message = "Shteti nuk mund te jete Serbia!")
    private String state;

    @Column(nullable = false, length = 10)
    @Size(min = 5, max = 10, message = "Zipi duhet te jete 5 deri ne 10 karaktere")
    private String zip;

    @Column(length = 50)
    @Email(message = "Emaila duhet me qene valide")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Emaila duhet te permbaje @")
    @Size(max = 50)
    private String email;

    @Column(length = 25)
    @Size(max = 25)
    private String phone;

    @Column(nullable = false)
    @PastOrPresent(message = "Data e regjistrimit duhet te jete ne te kaluaren ose ne diten e sotme")
    @NotNull
    private LocalDate registeredDate;


    @Column(length = 1000)
    @Size(max = 1000)
    private String documentUrl;


    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, length = 50, updatable = false)
    private String createdBy = "admin";

    private LocalDateTime modifiedAt;

    @Column(length = 50)
    @Size(max = 50)
    private String modifiedBy;


}
