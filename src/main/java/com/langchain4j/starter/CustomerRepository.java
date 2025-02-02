package com.langchain4j.starter;

import com.langchain4j.starter.dto.Booking;
import com.langchain4j.starter.dto.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);
}
