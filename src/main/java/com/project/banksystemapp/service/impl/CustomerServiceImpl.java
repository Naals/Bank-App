package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.modal.Customer;
import com.project.banksystemapp.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public Customer getCustomer(Long id) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return List.of();
    }
}
