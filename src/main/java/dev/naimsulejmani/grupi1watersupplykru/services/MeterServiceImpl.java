package dev.naimsulejmani.grupi1watersupplykru.services;

import dev.naimsulejmani.grupi1watersupplykru.models.Meter;
import dev.naimsulejmani.grupi1watersupplykru.repositories.MeterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterServiceImpl implements MeterService {
    private final MeterRepository meterRepository;

    public MeterServiceImpl(MeterRepository meterRepository) {
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

    @Override
    public Meter add(Meter meter) {
        if (meter.getId() > 0) {
            var existMeter = meterRepository.existsById(meter.getId());
            if (existMeter) {
                System.out.println("Meter already exists with id = " + meter.getId());
                return null;
            }
        }

        var existsMeter = meterRepository.findBySerialNo(meter.getSerialNo());
        if (existsMeter.isPresent()) {
            System.out.println("Meter already exists with serial number = " + meter.getSerialNo());
            return null;
        }

        return meterRepository.save(meter);
    }

    @Override
    public Meter modify(Meter meter) {
        if (meter.getId() == 0) {
            System.out.println("Meter id is required");
            return null;
        }
        var existsMeter = meterRepository.findById(meter.getId());
        if (existsMeter.isEmpty()) {
            System.out.println("Meter does not exist with id = " + meter.getId());
            return null;
        }
        return meterRepository.save(meter);
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

    @Override
    public Meter findBySerialNo(String serialNo) {
        return meterRepository.findBySerialNo(serialNo).orElse(null);
    }
}








