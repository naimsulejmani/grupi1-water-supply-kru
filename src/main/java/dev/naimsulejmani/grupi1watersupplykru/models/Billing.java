package dev.naimsulejmani.grupi1watersupplykru.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "billings")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne
    @JoinColumn(name = "reading_id", nullable = false)
    private Reading reading;



    private LocalDate billingDate;

    private double previousReading;

    private double totalReading;
    private double pricePerCubicMeter;
    private double totalNetAmount;
    private double vat;
    private double totalAmount;

}















