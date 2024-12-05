package dev.naimsulejmani.grupi1watersupplykru.repositories;

import dev.naimsulejmani.grupi1watersupplykru.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {

    Optional<Meter> findBySerialNo(String serialNo); // SELECT * FROM meters WHERE serial_no = ?;

    List<Meter> findAllByMeterType(String meterType); // SELECT * FROM meters WHERE meter_type = ?;

    List<Meter> findAllByLocationContaining(String location); // SELECT * FROM meters WHERE location LIKE ?;

    List<Meter> findAllByPassiveOrderByInstallationDateDesc(boolean passive);
    // SELECT * FROM meters WHERE passive = ? ORDER BY installation_date DESC;

    List<Meter> findAllByLocationOrMeterTypeOrderByCreatedAt(String location, String meterType);
    // SELECT * FROM meters WHERE location = ? OR meter_type = ? ORDER BY created_at;

    List<Meter> findAllByLocationAndMeterTypeOrderByCreatedAt(String location, String meterType);
    // SELECT * FROM meters WHERE location = ? AND meter_type = ? ORDER BY created_at;
}
