package dev.naimsulejmani.grupi1watersupplykru.services;

import dev.naimsulejmani.grupi1watersupplykru.models.Meter;

import java.util.List;

public interface MeterService {
    List<Meter> findAll(); // SELECT * FROM meters;

    Meter findById(long id); // SELECT * FROM meters WHERE id = ?;

    Meter add(Meter meter); // INSERT INTO meters (serial_no, installation_date, location, last_reading, last_reading_date, meter_type, created_at, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?);

    Meter modify(Meter meter); // UPDATE meters SET ... WHERE id=id

    void deleteById(long id); // DELETE FROM meters WHERE id = ?;

    Meter findBySerialNo(String serialNo); // SELECT * FROM meters WHERE serial_no = ?;
}