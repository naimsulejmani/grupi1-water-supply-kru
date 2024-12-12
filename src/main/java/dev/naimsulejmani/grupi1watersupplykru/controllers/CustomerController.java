package dev.naimsulejmani.grupi1watersupplykru.controllers;

import dev.naimsulejmani.grupi1watersupplykru.dtos.UserDto;
import dev.naimsulejmani.grupi1watersupplykru.helpers.FileHelper;
import dev.naimsulejmani.grupi1watersupplykru.models.Customer;
import dev.naimsulejmani.grupi1watersupplykru.models.User;
import dev.naimsulejmani.grupi1watersupplykru.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FileHelper fileHelper;

    public CustomerController(CustomerService customerService, FileHelper fileHelper) {
        this.customerService = customerService;
        this.fileHelper = fileHelper;
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
        model.addAttribute("fromDate", LocalDate.now().minusMonths(3));
        model.addAttribute("toDate", LocalDate.now());
        return "customers/new";
    }

    @PostMapping("/new")
    public String newCustomer(
            @Valid @ModelAttribute Customer customer
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , @RequestParam("documentFile") MultipartFile documentFile
            , HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "customers/new";
        }

        System.out.println("Document file: " + documentFile.getOriginalFilename());

        if (!documentFile.isEmpty()) {
            try {
                String newFilename = fileHelper.uploadFile("target/classes/static/assets/images/customers",
                        documentFile.getOriginalFilename(), documentFile.getBytes());

                if (newFilename != null) {
                    customer.setDocumentUrl("/assets/images/customers/" + newFilename);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }


        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        customer.setCreatedBy(userDto.getUsername());
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
    public String editCustomer(
            @Valid @ModelAttribute Customer customer
            , BindingResult bindingResult
            , @PathVariable Long id
            , RedirectAttributes redirectAttributes
            , @RequestParam("documentFile") MultipartFile documentFile,
            @SessionAttribute("user") UserDto userDto) {
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
        customer.setModifiedBy(userDto.getUsername());

        if (!documentFile.isEmpty()) {
            try {
                String newFilename = fileHelper.uploadFile("target/classes/static/assets/images/customers",
                        documentFile.getOriginalFilename(), documentFile.getBytes());

                if (newFilename != null) {
                    customer.setDocumentUrl("/assets/images/customers/" + newFilename);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }


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










