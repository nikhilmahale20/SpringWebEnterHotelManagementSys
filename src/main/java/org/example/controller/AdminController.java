package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    public AdminController() {
        System.out.println("AdminController Loaded");
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(
            HttpSession session,
            Model model
    ) {
        System.out.println(
                "ADMIN SESSION = "
                        + session.getAttribute("admin")
        );
        if(session.getAttribute("admin") == null){
            return "redirect:/login";
        }

        return "customer-dashboard";
    }
}