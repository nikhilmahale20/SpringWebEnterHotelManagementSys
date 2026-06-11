package org.example.service;

import org.example.dto.InvoiceDTO;
import org.example.entity.Booking;
import org.example.entity.Payment;
import org.example.enums.PaymentStatus;
import org.example.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(
            PaymentRepository paymentRepository
    ) {
        this.paymentRepository = paymentRepository;
    }

    public InvoiceDTO generateInvoice(
            Booking booking
    ) {

        long nights =
                ChronoUnit.DAYS.between(
                        booking.getCheckInDate(),
                        booking.getCheckOutDate()
                );

        BigDecimal roomCost =
                BigDecimal.valueOf(
                                booking.getRoom()
                                        .getPricePerNight()
                        )
                        .multiply(
                                BigDecimal.valueOf(
                                        nights
                                )
                        );

        BigDecimal tax =
                roomCost.multiply(
                        new BigDecimal("0.18")
                );

        BigDecimal totalAmount =
                roomCost.add(
                        tax
                );

        return new InvoiceDTO(

                booking.getBookingId(),

                booking.getCustomer()
                        .getName(),

                booking.getRoom()
                        .getRoomNumber(),

                roomCost,

                tax,

                totalAmount
        );
    }

    public void processPayment(
            Booking booking
    ) {

        InvoiceDTO invoice =
                generateInvoice(
                        booking
                );

        Payment payment =
                new Payment();

        payment.setBooking(
                booking
        );

        payment.setAmount(
                invoice.totalAmount()
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        payment.setPaymentStatus(
                PaymentStatus.COMPLETED
        );

        Payment existingPayment =
                paymentRepository.findByBookingId(
                        booking.getBookingId()
                );

        if(existingPayment != null){

            throw new RuntimeException(
                    "Payment already exists for this booking."
            );
        }

        paymentRepository.savePayment(payment);

    }

    public List<Payment>
    getPaymentsByCustomer(
            Long customerId
    ) {
        System.out.println(
                "Searching Payments For Customer = "
                        + customerId
        );
        return paymentRepository
                .findPaymentsByCustomerId(
                        customerId
                );

    }
    
}