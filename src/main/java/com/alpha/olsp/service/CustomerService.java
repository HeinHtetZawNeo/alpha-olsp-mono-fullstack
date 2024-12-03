package com.alpha.olsp.service;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Customer;

public interface CustomerService {
    AuthenticationResponseDto register(Customer customer);
}
