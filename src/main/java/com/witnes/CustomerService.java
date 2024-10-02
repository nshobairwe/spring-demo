package com.witnes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Retrieve all customers
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Add a new customer
    public void addCustomer(CustomerController.NewCustomerRequest request) {
        CustomerModel customer = new CustomerModel();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(Long.valueOf(customerId));
    }

    // Update a customer by ID
    public void updateCustomer(Integer customerId, CustomerController.NewCustomerRequest request) {
        Optional<CustomerModel> optionalCustomer = customerRepository.findById(Long.valueOf(customerId));

        if (optionalCustomer.isPresent()) {
            CustomerModel customer = optionalCustomer.get();
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setAge(request.age());
            customerRepository.save(customer);
        } else {
            throw new IllegalStateException("Customer with ID " + customerId + " does not exist.");
        }
    }
}
