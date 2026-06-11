package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.enums.Role;
import org.example.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Customer;

@Controller
public class AuthController {

    private final CustomerService customerService;

    public AuthController(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ) {

        try {

            Customer customer =
                    customerService.login(
                            email,
                            password
                    );
            System.out.println(
                    "ROLE = " + customer.getRole()
            );
            session.setAttribute(
                    "customer",
                    customer
            );

            Role role =
                    customer.getRole();

            if(role == null){
                throw new RuntimeException(
                        "Role Not Assigned"
                );
            }

             switch (
                    customer.getRole()
                    ) {

                 case ADMIN -> {
                     session.setAttribute(
                             "admin",
                             customer
                     );
                     return "redirect:/admin/dashboard";
                 }

                 case CUSTOMER -> {
                     session.setAttribute(
                             "customer",
                             customer
                     );
                     return "redirect:/customer/dashboard";
                 }

                 case RECEPTIONIST -> {
                     session.setAttribute(
                             "receptionist",
                             customer
                     );
                     return "redirect:/receptionist/dashboard";
                 }
                 default -> {
                     throw new RuntimeException(
                             "Invalid Role"
                     );
                 }
             }

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            Model model
    ) {

        try {

            Customer customer =
                    new Customer();

            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setPasswordHash(password);
            customer.setRole(
                    Role.valueOf(role)
            );

            customerService
                    .registerCustomer(
                            customer
                    );

            return "redirect:/login";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "register";
        }
    }


    @GetMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        if (session != null) {

            session.invalidate();
        }

        return "redirect:/login";
    }
}