package dev.naimsulejmani.grupi1watersupplykru.repositories;

import dev.naimsulejmani.grupi1watersupplykru.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}
