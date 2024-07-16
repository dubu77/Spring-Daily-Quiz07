package com.example.springdailyquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/index")
    public String test(Model model) {
        User user = new User("John Doe", "john@example.com", "password", true);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", 10.99),
                new Product(2L, "Product 2", 20.99),
                new Product(3L, "Product 3", 30.99)
        );
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        System.out.println("Received user registration:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        return "redirect:/index";
    }
}
