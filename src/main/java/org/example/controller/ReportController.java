package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.entity.Customer;
import org.example.enums.Role;
import org.example.thread.OccupancyReportTask;
import org.example.thread.RevenueReportTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class ReportController {

    @GetMapping("/reports")
    public String reports(
            HttpSession session,
            Model model
    ) throws Exception {

        Customer customer =
                (Customer) session.getAttribute(
                        "loggedInCustomer"
                );

        if (customer == null ||
                customer.getRole() != Role.ADMIN) {

            return "redirect:/login";
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        try {

            Future<BigDecimal> revenueFuture =
                    executor.submit(
                            new RevenueReportTask()
                    );

            Future<Double> occupancyFuture =
                    executor.submit(
                            new OccupancyReportTask()
                    );

            BigDecimal revenue =
                    revenueFuture.get();

            Double occupancy =
                    occupancyFuture.get();

            model.addAttribute(
                    "revenue",
                    revenue
            );

            model.addAttribute(
                    "occupancy",
                    occupancy
            );

            return "report";

        } finally {

            executor.shutdown();
        }
    }
}