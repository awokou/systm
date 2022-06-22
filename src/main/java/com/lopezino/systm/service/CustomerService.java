package com.lopezino.systm.service;

import com.lopezino.systm.exception.BadRequestException;
import com.lopezino.systm.exception.CustomerNotFoundException;
import com.lopezino.systm.model.Customer;
import com.lopezino.systm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerById(Long customerId){
        Customer customers = customerRepository.findById(customerId).get();
        return customers;
    }

    public  Customer addCustomer(Customer customer) {
        Boolean existsEmail = customerRepository.selectExistsEmail(customer.getEmail());
        if (existsEmail) {
            throw new BadRequestException("Email " + customer.getEmail() + " taken");
        }
       return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer with id " + customerId + " does not exists");
        }
        customerRepository.deleteById(customerId);
    }
}
