package dev.naimsulejmani.grupi1watersupplykru.services;

import dev.naimsulejmani.grupi1watersupplykru.models.Customer;
import dev.naimsulejmani.grupi1watersupplykru.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 5 methods findAll, findById, add, modify, deleteById
    public List<Customer> findAll() {
        return customerRepository.findAll(); // SELECT * FROM customers;
    }

    public Customer findById(long id) {
        return customerRepository.findById(id).orElse(null); // SELECT * FROM customers WHERE id = ?;
    }

    public Customer add(Customer customer) {
        var existCustomer = findById(customer.getId());
        if (existCustomer != null) {
            return null;
        }
        return customerRepository.save(customer);
    }

    public Customer modify(Customer customer) {
        var existCustomer = findById(customer.getId());
        if (existCustomer == null) {
            return null;
        }
        return customerRepository.save(customer);
    }

    public void deleteById(long id) {
        var existCustomer = findById(id);
        if (existCustomer == null) {
            System.out.println("NUk ekziston customer me id = " + id);
            return;
        }
        customerRepository.deleteById(id); // DELETE FROM customers WHERE id = ?;
    }

}











