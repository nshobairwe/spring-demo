package com.witnes;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<CustomerModel> getCustomers() {
        return customerService.getAllCustomers();
    }

    // Add a new customer
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        customerService.addCustomer(request);
    }

    // Delete a customer by ID
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

    // Update a customer by ID
    @PutMapping("/{customerId}")
    public void updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody NewCustomerRequest request) {
        customerService.updateCustomer(customerId, request);
    }

    // Record class for capturing request data
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}
}
