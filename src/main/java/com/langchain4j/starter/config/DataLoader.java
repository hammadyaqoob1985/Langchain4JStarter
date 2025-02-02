package com.langchain4j.starter.config;


import com.langchain4j.starter.BookingRepository;
import com.langchain4j.starter.CustomerRepository;
import com.langchain4j.starter.dto.Booking;
import com.langchain4j.starter.dto.BookingClass;
import com.langchain4j.starter.dto.BookingStatus;
import com.langchain4j.starter.dto.Customer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public DataLoader(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }


    @PostConstruct
    public void init() {
        Customer johnCustomer = new Customer();
        johnCustomer.setFirstName("John");
        johnCustomer.setLastName("Doe");

        Customer janeCustomer = new Customer();
        janeCustomer.setFirstName("Jane");
        janeCustomer.setLastName("Smith");

        Customer michaelCustomer = new Customer();
        michaelCustomer.setFirstName("Michael");
        michaelCustomer.setLastName("Johnson");

        Customer sarahCustomer = new Customer();
        sarahCustomer.setFirstName("Sarah");
        sarahCustomer.setLastName("Williams");

        Customer robertCustomer = new Customer();
        robertCustomer.setFirstName("Robert");
        robertCustomer.setLastName("Taylor");

        customerRepository.saveAll(List.of(johnCustomer, janeCustomer, michaelCustomer, sarahCustomer, robertCustomer));


       customerRepository.findCustomersByFirstNameAndLastName("John", "Doe").ifPresent(this::generateBookingForCustomer);
       customerRepository.findCustomersByFirstNameAndLastName("Jane", "Smith").ifPresent(this::generateBookingForCustomer);
       customerRepository.findCustomersByFirstNameAndLastName("Michael", "Johnson").ifPresent(this::generateBookingForCustomer);
       customerRepository.findCustomersByFirstNameAndLastName("Sarah", "Williams").ifPresent(this::generateBookingForCustomer);
       customerRepository.findCustomersByFirstNameAndLastName("Robert", "Taylor").ifPresent(this::generateBookingForCustomer);


    }

    private void generateBookingForCustomer(Customer customer) {
        List<String> airportCodes = List.of("LAX", "SFO", "JFK", "LHR", "CDG", "ARN", "HEL", "TXL", "MUC", "FRA", "MAD", "SJC");
        Random random = new Random();
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setBookingClass(BookingClass.values()[random.nextInt(BookingClass.values().length)]);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        LocalDate bookingDate = LocalDate.now().plusDays(random.nextInt(30) + 1);
        booking.setDate(bookingDate);
        booking.setReturnDate(bookingDate.plusDays(7));
        booking.setSource(airportCodes.get(random.nextInt(airportCodes.size())));
        booking.setDestination(airportCodes.get(random.nextInt(airportCodes.size())));
        Booking savedBooking = bookingRepository.save(booking);
        customer.setBookings(List.of(savedBooking));
        customerRepository.save(customer);

    }

}
