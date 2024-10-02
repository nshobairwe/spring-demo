package com.witnes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication // Use @SpringBootApplication instead of @EnableAutoConfiguration, @ComponentScan, and @Configuration
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // Correctly start the Spring application
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepository.findAll(); // Retrieve actual customers from the repository
    }
}
