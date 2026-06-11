package org.example.dto;

import java.time.LocalDate;

public record BookingRequest(

        Long roomId,

        LocalDate checkInDate,

        LocalDate checkOutDate

) {
}