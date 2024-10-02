package com.witnes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // Add a new customer
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    // Delete a customer by ID
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerRepository.deleteById(Long.valueOf(customerId));
    }

    // Update a customer by ID
    @PutMapping("/{customerId}")
    public void updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody NewCustomerRequest request) {
        Optional<Customer> optionalCustomer = customerRepository.findById(Long.valueOf(customerId));

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setAge(request.age());
            customerRepository.save(customer);
        } else {
            throw new IllegalStateException("Customer with ID " + customerId + " does not exist.");
        }
    }

    // Record class for capturing request data
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}
}
