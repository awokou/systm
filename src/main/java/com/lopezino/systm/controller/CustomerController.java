package com.lopezino.systm.controller;

import com.lopezino.systm.model.Customer;
import com.lopezino.systm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {

        Customer customers = customerService.getCustomerById(customerId);

        return customers;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {

        Customer customer1 = customerService.addCustomer(customer);

        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {

        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/customers/firstName/{firstName}")
    public List<Customer> findByFirstName(@PathVariable String firstName) {

        return customerService.findByFirstName(firstName);
    }
}
