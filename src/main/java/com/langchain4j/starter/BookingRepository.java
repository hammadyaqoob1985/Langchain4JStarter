package com.langchain4j.starter;

import com.langchain4j.starter.dto.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Optional<Booking> findBookingsByBookingIdAndCustomer_FirstNameAndCustomer_LastName(Long bookingId, String firstName, String lastName);
}
