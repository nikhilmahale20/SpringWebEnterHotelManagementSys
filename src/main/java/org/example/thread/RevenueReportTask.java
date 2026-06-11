package org.example.thread;

import org.example.entity.Payment;
import org.example.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;

public class RevenueReportTask
        implements Callable<BigDecimal> {

    private final PaymentRepository paymentRepository =
            new PaymentRepository();

    @Override
    public BigDecimal call() {

        List<Payment> payments =
                paymentRepository.getAllPayments();

        return payments.stream()

                .map(
                        Payment::getAmount
                )

                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }
}