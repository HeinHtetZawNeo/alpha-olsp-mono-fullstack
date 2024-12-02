package com.alpha.olsp.repository.impl;

import com.alpha.olsp.model.Admin;
import com.alpha.olsp.model.Customer;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.model.User;
import com.alpha.olsp.repository.AdminRepository;
import com.alpha.olsp.repository.CustomerRepository;
import com.alpha.olsp.repository.SellerRepository;
import com.alpha.olsp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Optional<User> findByUsername(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            return Optional.of(customerOptional.get());
        }
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(email);
        if (sellerOptional.isPresent()) {
            return Optional.of(sellerOptional.get());
        }
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return Optional.of(adminOptional.get());
        }
        return Optional.empty();
    }
}
