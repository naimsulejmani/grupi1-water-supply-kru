package dev.naimsulejmani.grupi1watersupplykru.controllers;

import dev.naimsulejmani.grupi1watersupplykru.models.Customer;
import dev.naimsulejmani.grupi1watersupplykru.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            if (error.equals("SUCCESS")) {
                model.addAttribute("success", "Customer added successfully!");
            }
        }
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
    public String newCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "customers/new";
        }

        customerService.add(customer);
        redirectAttributes.addAttribute("errorId", "SUCCESS");
        redirectAttributes.addFlashAttribute("success", "Consumer Successfully registererd!");
        return "redirect:/customers";
    }


    @GetMapping("/{id}/edit")
    public String editCustomer(Model model, @PathVariable Long id) {
        var customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    @PostMapping("/{id}/edit")
    public String editCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "customers/edit";
        }

        if (customer.getId() != id) {
//            bindingResult.addError(new ObjectError("customer", "Customer ID mismatch!"));
//            return "customers/edit";
//            // shto nje atribut ne URL qe tregon gabimin
//            //
            redirectAttributes.addAttribute("errorId", "CS404");
            // shto nje objjekt ne faqen qe po e ridireton
            redirectAttributes.addFlashAttribute("error", "Customer ID mismatch!");
            return "redirect:/customers";
        }
        customer.setModifiedAt(LocalDateTime.now());
        customer.setModifiedBy("admin");
        customerService.modify(customer);
        return "redirect:/customers";
    }

    @GetMapping("{id}/details")
    public String detailCustomer(Model model, @PathVariable Long id) {
        var customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/detail";
    }

    @GetMapping("{id}/delete")
    public String deleteCustomer(Model model, @PathVariable Long id) {
        var customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/delete";
    }

    @PostMapping("{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}










