package dev.naimsulejmani.grupi1watersupplykru.controllers;

import dev.naimsulejmani.grupi1watersupplykru.dtos.LoginRequestDto;
import dev.naimsulejmani.grupi1watersupplykru.dtos.RegisterUserRequestDto;
import dev.naimsulejmani.grupi1watersupplykru.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequestDto loginRequestDto,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "returnUrl", required = false) String returnUrl) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        var userDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        if (userDto == null) {
            bindingResult.rejectValue("email", "error.loginRequestDto", "Emaili ose passwordi i gabuar!");
            bindingResult.rejectValue("password", "error.loginRequestDto", "Emaili ose passwordi i gabuar!");
            return "auth/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", userDto);

        Cookie cookie = new Cookie("userId", "" + userDto.getId());
        if (loginRequestDto.isRememberMe()) {
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        } else {
            cookie.setMaxAge(60 * 60); // 1 hour
        }

        response.addCookie(cookie);
        if (returnUrl == null || returnUrl.isBlank())
            return "redirect:/";
        return "redirect:" + returnUrl;
    }


    @GetMapping("/reset-password")
    public String resetPassword() {
        return "auth/reset-password";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerUserRequestDto", new RegisterUserRequestDto());
        model.addAttribute("maxDate", LocalDate.now().minusYears(18));
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserRequestDto registerUserRequestDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (!registerUserRequestDto.getPassword().equals(registerUserRequestDto.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.registerUserRequestDto", "Passwordet nuk perputhen");
            bindingResult.rejectValue("confirmPassword", "error.registerUserRequestDto", "Passwordet nuk perputhen");
            return "auth/register";
        }




        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/login";
    }
}








