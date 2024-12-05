package dev.naimsulejmani.grupi1watersupplykru.controllers;


import dev.naimsulejmani.grupi1watersupplykru.enums.MeterType;
import dev.naimsulejmani.grupi1watersupplykru.models.Meter;
import dev.naimsulejmani.grupi1watersupplykru.services.MeterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Random;

@Controller
public class HomeController {
    private final MeterServiceImpl meterService;

    public HomeController(MeterServiceImpl meterService) {
        this.meterService = meterService;
    }

    @GetMapping("")
    public String home() {
        Meter meter = new Meter();
        meter.setSerialNo("" + new Random().nextInt(1_000_000_000, 2_000_000_000));
        meter.setMeterType(MeterType.RESIDENTIAL);
        meter.setLocation("Te bunari i hajratit");
        meter.setInstallationDate(LocalDate.now());
        meter.setLastReading(5.5);

        this.meterService.save(meter);

        return "index";
    }
}
