package org.example.controller;

import org.example.entity.Booking;
import org.example.entity.Payment;
import org.example.repository.BookingRepository;
import org.example.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.example.entity.Customer;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingRepository bookingRepository;

    public PaymentController(
            PaymentService paymentService,
            BookingRepository bookingRepository
    ) {
        this.paymentService = paymentService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/pay")
    public String pay(
            @RequestParam("id") Long id
    ) {

        System.out.println("PAY BUTTON CLICKED");
        System.out.println("BOOKING ID = " + id);

        try {

            Booking booking =
                    bookingRepository.findById(id);
            System.out.println("BOOKING = " + booking);
            paymentService.processPayment(
                    booking
            );

        } catch (RuntimeException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return "redirect:/bookings/customer-bookings";
    }

    @GetMapping("/my-payments")
    public String myPayments(
            HttpSession session,
            Model model
    ) {

        Customer customer =
                (Customer) session.getAttribute("customer");

        if(customer == null){
            return "redirect:/login";
        }

        model.addAttribute(
                "payments",
                paymentService.getPaymentsByCustomer(
                        customer.getCustomerId()
                )
        );

        return "my-payments";
    }
}