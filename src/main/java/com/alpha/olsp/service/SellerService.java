package com.alpha.olsp.service;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Seller;

import java.util.List;

public interface SellerService {
    AuthenticationResponseDto register(Seller seller);
    List<Seller> getSellers();
}
