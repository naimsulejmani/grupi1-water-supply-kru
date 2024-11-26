package dev.naimsulejmani.grupi1watersupplykru.repositories;

import dev.naimsulejmani.grupi1watersupplykru.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
