package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.modal.Customer;
import com.project.banksystemapp.repository.CustomerRepository;
import com.project.banksystemapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Customer not found")
        );

        customer.setFullName(customerToUpdate.getFullName());
        customer.setEmail(customerToUpdate.getEmail());
        customer.setPhone(customerToUpdate.getPhone());

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Customer not found")
        );

        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomer(Long id) {

        return customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Customer not found")
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(Map<String, String> keywords) {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                keywords.get("name"), keywords.get("email"), keywords.get("phone")
        );
    }
}
