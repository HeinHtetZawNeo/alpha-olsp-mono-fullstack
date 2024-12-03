package com.alpha.olsp.service;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Seller;

public interface SellerService {
    AuthenticationResponseDto register(Seller seller);
}
