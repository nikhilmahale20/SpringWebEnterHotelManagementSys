package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    public CustomerController() {
        System.out.println("CustomerController Loaded");
    }
    @GetMapping("/customer/dashboard")
    public String customerDashboard(
            HttpSession session,
            Model model
    ) {

        Customer customer =
                (Customer) session.getAttribute(
                        "customer"
                );

        if (customer == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "customer",
                customer
        );

        return "customer-dashboard";
    }
}