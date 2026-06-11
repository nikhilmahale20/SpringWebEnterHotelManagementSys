package org.example.dto;

import java.math.BigDecimal;

public record InvoiceDTO(

        Long bookingId,

        String customerName,

        String roomNumber,

        BigDecimal roomCost,

        BigDecimal tax,

        BigDecimal totalAmount

) {
}