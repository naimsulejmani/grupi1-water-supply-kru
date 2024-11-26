package dev.naimsulejmani.grupi1watersupplykru.models;

import dev.naimsulejmani.grupi1watersupplykru.enums.MeterType;
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
@Entity(name = "meters")
//@Table(name = "meters", schema = "watersupply")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String serialNo; //serial_no
    @Column(nullable = false)
    private LocalDate installationDate; //installation_date
    @Column(length = 500, nullable = false)
    private String location;
    @Column(nullable = false)
    private double lastReading;
    private LocalDate lastReadingDate;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MeterType meterType;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, length = 25)
    private String createdBy = "admin";
    private LocalDateTime modifiedAt;
    @Column(length = 25)
    private String modifiedBy;

}
