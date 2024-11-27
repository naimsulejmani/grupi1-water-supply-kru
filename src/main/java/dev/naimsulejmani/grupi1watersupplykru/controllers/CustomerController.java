package dev.naimsulejmani.grupi1watersupplykru.controllers;

import dev.naimsulejmani.grupi1watersupplykru.models.Customer;
import dev.naimsulejmani.grupi1watersupplykru.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String index(Model model) {
        var customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/index";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/new";
    }

    @PostMapping("/new")
    public String newCustomer(@ModelAttribute Customer customer) {
        customerService.add(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editCustomer(Model model, @PathVariable Long id) {
        var customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    @PostMapping("/{id}/edit")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.modify(customer);
        return "redirect:/customers";
    }

}










