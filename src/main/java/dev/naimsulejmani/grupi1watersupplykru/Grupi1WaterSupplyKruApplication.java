package dev.naimsulejmani.grupi1watersupplykru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;

import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Grupi1WaterSupplyKruApplication {

    public static void main(String[] args) {
        SpringApplication.run(Grupi1WaterSupplyKruApplication.class, args);
    }

}
