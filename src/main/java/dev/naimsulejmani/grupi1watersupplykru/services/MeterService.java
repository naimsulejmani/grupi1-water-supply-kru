package dev.naimsulejmani.grupi1watersupplykru.services;

import dev.naimsulejmani.grupi1watersupplykru.models.Meter;
import dev.naimsulejmani.grupi1watersupplykru.repositories.MeterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterService {
    private final MeterRepository meterRepository;

    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public List<Meter> findAll() {
        return meterRepository.findAll();
        //SELECT * FROM meters;
    }

    public Meter findById(long id) {
//        Optional<Meter> meter = meterRepository.findById(id);
//        if(meter.isEmpty())
//            return null;
//        return meter.get();
        return meterRepository.findById(id).orElse(null);
        //SELECT * FROM meters WHERE id = ?;
    }

    public Meter save(Meter meter) {
        return meterRepository.save(meter);
        // INSERT INTO meters (serial_no, installation_date, location, last_reading, last_reading_date, meter_type, created_at, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        //OR
        // UPDATE meters SET ... WHERE id=id
    }

    public void deleteById(long id) {
        meterRepository.deleteById(id);
        //DELETE FROM meters WHERE id = ?;
    }
}








