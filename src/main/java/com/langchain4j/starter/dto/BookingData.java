package com.langchain4j.starter.dto;

import java.util.ArrayList;
import java.util.List;

public class BookingData {

    private List<Customer> customers = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public BookingData() {
    }

    public BookingData(List<Customer> customers, List<Booking> bookings) {
        this.customers = customers;
        this.bookings = bookings;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
