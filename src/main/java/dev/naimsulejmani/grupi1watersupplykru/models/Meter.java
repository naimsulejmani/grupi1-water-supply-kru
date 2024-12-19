package dev.naimsulejmani.grupi1watersupplykru.models;

import dev.naimsulejmani.grupi1watersupplykru.enums.MeterType;
import dev.naimsulejmani.grupi1watersupplykru.infrastructure.validations.StartsWith;
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
@Entity(name = "meters")
//@Table(name = "meters", schema = "watersupply")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "ID duhet te jete numer pozitiv ose zero!")
    private long id;

    @Column(unique = true, nullable = false, length = 20)
    @Size(max = 20, message = "Serial number duhet te jete me shkruajt deri ne 20 karaktere!")
    @NotBlank(message = "Serial number nuk mund te jete i zbrazet!")
//    @StartsWith(value = "XK-100", message = "Serial number duhet te filloj me 100!")
    private String serialNo; //serial_no

    @Column(nullable = false)
    @NotNull(message = "Data e instalimit nuk mund te jete null!")
    @PastOrPresent(message = "Data e instalimit nuk mund te jete ne te ardhmen!")
    private LocalDate installationDate; //installation_date


    @Column(length = 500, nullable = false)
    @Size(max = 500, message = "Vendndodhja duhet te jete me shkruajt deri ne 500 karaktere!")
    @NotBlank(message = "Vendndodhja nuk mund te jete i zbrazet!")
    @NotNull(message = "Vendndodhja nuk mund te jete null!")
    private String location;

    @Column(nullable = false)
    @NotNull(message = "Leximi fillestar nuk mund te jete null!")
    @PositiveOrZero(message = "Leximi fillestar duhet te jete numer pozitiv ose zero!")
    private double lastReading;

    private LocalDate lastReadingDate;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MeterType meterType;


    private boolean passive;

    @Column(length = 500, nullable = true)
    @Size(max = 500)
    private String installationPhoto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, length = 25, updatable = false)
    @Size(max = 25)
    private String createdBy = "admin";

    private LocalDateTime modifiedAt;

    @Column(length = 25)
    @Size(max = 25)
    private String modifiedBy;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
