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
@Entity(name = "readings")
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private double readingValue;

    @Column(nullable = false)
    private LocalDate readingDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, length = 50)
    private String createdBy;
    private LocalDateTime modifiedAt;
    @Column(length = 50)
    private String modifiedBy;

    @OneToOne(mappedBy = "reading")
    private Billing billing;

}









