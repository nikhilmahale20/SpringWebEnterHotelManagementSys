package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.entity.Booking;
import org.example.entity.Customer;
import org.example.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService
    ) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String myBookings(
            HttpSession session,
            Model model
    ) {

        Customer customer =
                (Customer) session.getAttribute(
                        "customer"
                );

        if(customer == null){
            return "redirect:/login";
        }

        model.addAttribute(
                "bookings",
                bookingService.getCustomerBookings(
                        customer.getCustomerId()
                )
        );

        return "customer-bookings";
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(
            @PathVariable("id") Long id
    ) {

        bookingService.cancelBooking(id);

        return "redirect:/bookings";
    }
    @GetMapping("/customer-bookings")
    public String customerBookings(
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

        List<Booking> bookings =
                bookingService.getCustomerBookings(
                        customer.getCustomerId()
                );

        model.addAttribute(
                "bookings",
                bookings
        );

        return "customer-bookings";
    }

    @GetMapping("/book/{roomId}")
    public String bookRoomPage(
            @PathVariable("roomId") Long roomId,
            Model model,
            HttpSession session
    ) {

        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }

        model.addAttribute(
                "roomId",
                roomId
        );

        return "book-room";
    }

    @PostMapping("/save")
    public String saveBooking(
            @RequestParam("roomId") Long roomId,
            @RequestParam("checkInDate") String checkInDate,
            @RequestParam("checkOutDate") String checkOutDate,
            HttpSession session
    ) {

        Customer customer =
                (Customer) session.getAttribute(
                        "customer"
                );

        if(customer == null){
            return "redirect:/login";
        }

        bookingService.bookRoom(
                customer,
                roomId,
                java.time.LocalDate.parse(checkInDate),
                java.time.LocalDate.parse(checkOutDate)
        );

        return "redirect:/bookings/customer-bookings";
    }



}