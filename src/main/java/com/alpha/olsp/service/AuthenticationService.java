package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.AuthenticationRequestDto;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;

public interface AuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest);
}
