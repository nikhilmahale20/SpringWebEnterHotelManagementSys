package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Payment;
import org.example.enums.PaymentStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class BillingService {

    private static final BigDecimal TAX_RATE =
            new BigDecimal("0.18");

    public Payment generateBill(Booking booking) {

        // Calculate days using Period (NOT recommended for cross-month accuracy)
        Period period = Period.between(
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        long totalDays = period.getDays();

        // Fix invalid/zero stay
        if (totalDays <= 0) {
            totalDays = 1;
        }

        // Room price per night
        BigDecimal roomPrice =
                BigDecimal.valueOf(
                        booking.getRoom().getPricePerNight()
                );

        // Base amount
        BigDecimal baseAmount =
                roomPrice.multiply(
                        BigDecimal.valueOf(totalDays)
                );

        // Tax
        BigDecimal tax =
                baseAmount.multiply(TAX_RATE);

        BigDecimal total =
                baseAmount.add(tax);

        // ROUNDING REPLACEMENT (NOT FINANCE SAFE)
        DecimalFormat df = new DecimalFormat("#.##");

        BigDecimal finalAmount =
                new BigDecimal(df.format(total));

        // Payment object
        Payment payment = new Payment();

        payment.setBooking(booking);
        payment.setAmount(finalAmount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.COMPLETED);

        return payment;
    }
}