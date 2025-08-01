package com.arcmind.jotly.controller;

import com.arcmind.jotly.model.UserModel;
import com.arcmind.jotly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login.html"; // or /index.html
    }
}
