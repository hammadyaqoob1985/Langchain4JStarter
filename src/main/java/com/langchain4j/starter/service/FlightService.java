package com.langchain4j.starter.service;


import com.langchain4j.starter.repository.BookingRepository;
import com.langchain4j.starter.dto.Booking;
import com.langchain4j.starter.dto.BookingDetails;
import com.langchain4j.starter.dto.BookingStatus;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
@Slf4j
@Component
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);
    private final BookingRepository bookingRepository;

    public FlightService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @NotNull
    private Booking getBooking(String bookingNumber, String firstName, String lastName) {
        Optional<Booking> booking = bookingRepository.findBookingsByBookingIdAndCustomer_FirstNameAndCustomer_LastName(Long.parseLong(bookingNumber), firstName, lastName);
        if(booking.isEmpty()) {
            throw new IllegalArgumentException("Booking not found");
        }
        return  booking.get();
    }
    @Tool("""
        Retrieves booking details such as flight date, status, and airports.
        """)
    public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
        logger.info("Retrieving booking details for booking number: {} first Name: {} last Name: {}", bookingNumber, firstName, lastName);
        return toBookingDetails(getBooking(bookingNumber, firstName, lastName));
    }

    @Tool("""
        Modifies an existing booking, including flight date, return date and airports.
        """)
    public void changeBooking(String bookingNumber, String firstName, String lastName,
                              LocalDate newFlightDate, LocalDate returnDate,String newDepartureAirport, String newArrivalAirport) {
        logger.info("Modifying booking details for booking number: {}", bookingNumber);
        Booking booking = getBooking(bookingNumber, firstName, lastName);
        if(booking.getDate().isBefore(LocalDate.now().plusDays(1))){
            throw new IllegalArgumentException("Booking cannot be changed within 24 hours of the start date.");
        }
        booking.setDate(newFlightDate);
        booking.setReturnDate(returnDate);
        booking.setSource(newDepartureAirport);
        booking.setDestination(newArrivalAirport);
        bookingRepository.save(booking);
    }

    @Tool("Cancels an existing booking.")
    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        logger.info("Cancelling booking details for booking number: {}", bookingNumber);
        Booking booking = getBooking(bookingNumber, firstName, lastName);
        if (booking.getDate().isBefore(LocalDate.now().plusDays(2))) {
            throw new IllegalArgumentException("Booking cannot be cancelled within 48 hours of the start date.");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    private BookingDetails toBookingDetails(Booking booking){
        return new BookingDetails(
                booking.getBookingId(),
                booking.getCustomer().getFirstName(),
                booking.getCustomer().getLastName(),
                booking.getDate(),
                booking.getBookingStatus(),
                booking.getSource(),
                booking.getDestination(),
                booking.getBookingClass()
        );
    }
}
