package com.witnes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers") // Updated to remove hardcoded ID
public class Main {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepository.findAll(); // Retrieve actual customers from the repository
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    // Updated deleteCustomer method
    @DeleteMapping("/{customerId}") // Use PathVariable for dynamic customer ID
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerRepository.deleteById(Long.valueOf(customerId));
    }
}
