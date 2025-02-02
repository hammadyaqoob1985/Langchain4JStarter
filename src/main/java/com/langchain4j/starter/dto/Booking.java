package com.langchain4j.starter.dto;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private LocalDate date;
    private LocalDate bookingTo;
    @OneToOne
    private Customer customer;
    private String source;
    private String destination;
    private BookingStatus bookingStatus;
    private BookingClass bookingClass;

    public Booking(Long bookingId, LocalDate date, LocalDate bookingTo, Customer customer, String source, String destination, BookingStatus bookingStatus, BookingClass bookingClass) {
        this.bookingId = bookingId;
        this.date = date;
        this.bookingTo = bookingTo;
        this.customer = customer;
        this.source = source;
        this.destination = destination;
        this.bookingStatus = bookingStatus;
        this.bookingClass = bookingClass;
    }

    public Booking() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getBookingTo() {
        return bookingTo;
    }

    public void setBookingTo(LocalDate bookingTo) {
        this.bookingTo = bookingTo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingClass getBookingClass() {
        return bookingClass;
    }

    public void setBookingClass(BookingClass bookingClass) {
        this.bookingClass = bookingClass;
    }
}
