package dev.naimsulejmani.grupi1watersupplykru.controllers;

import dev.naimsulejmani.grupi1watersupplykru.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}










