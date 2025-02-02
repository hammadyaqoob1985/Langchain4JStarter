package com.langchain4j.starter.dto;

import java.time.LocalDate;

public record BookingDetails(Long bookingNumber,
                             String firstName,
                             String lastName,
                             LocalDate date,
                             BookingStatus bookingStatus,
                             String source,
                             String destination,
                             BookingClass bookingClass) {
}
