package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.modal.Customer;
import com.project.banksystemapp.repository.CustomerRepository;
import com.project.banksystemapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private static final String CUSTOMER_NOT_FOUND = "Customer Not Found";

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(CUSTOMER_NOT_FOUND));

        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhone(customer.getPhone());

        return customerRepository.save(customerToUpdate);
    }


    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(CUSTOMER_NOT_FOUND)
        );

        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomer(Long id) {

        return customerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(CUSTOMER_NOT_FOUND)
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String name, String email, String phone) {
        return customerRepository
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                        name == null ? "" : name,
                        email == null ? "" : email,
                        phone == null ? "" : phone
                );
    }

}
