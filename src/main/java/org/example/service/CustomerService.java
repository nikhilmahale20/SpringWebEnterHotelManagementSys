package org.example.service;

import org.example.entity.Customer;
import org.example.exception.BusinessException;
import org.example.exception.InvalidCredentialException;
import org.example.repository.CustomerRepository;
import org.example.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository =
                customerRepository;
    }

    public void registerCustomer(
            Customer customer
    ) {

        if (customer.getEmail() == null
                || customer.getEmail().isBlank()) {

            throw new BusinessException(
                    "Email cannot be empty"
            );
        }

        customerRepository
                .findByEmail(
                        customer.getEmail()
                )
                .ifPresent(c -> {
                    throw new BusinessException(
                            "Email already registered"
                    );
                });

        String hashedPassword =
                PasswordUtil.hashPassword(
                        customer.getPasswordHash()
                );

        customer.setPasswordHash(
                hashedPassword
        );

        customerRepository.saveCustomer(
                customer
        );
    }

    public Customer login(
            String email,
            String password
    ) {

        Customer customer =
                customerRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new InvalidCredentialException(
                                                "Invalid Email or Password"
                                        )
                        );

        String hashedPassword =
                PasswordUtil.hashPassword(
                        password
                );

        if (!customer.getPasswordHash()
                .equals(hashedPassword)) {

            throw new InvalidCredentialException(
                    "Invalid Email or Password"
            );
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {

        return customerRepository
                .getAllCustomers();
    }
}