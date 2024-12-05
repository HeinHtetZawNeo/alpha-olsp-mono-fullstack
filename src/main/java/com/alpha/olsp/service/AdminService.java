package com.alpha.olsp.service;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Admin;

public interface AdminService {
    AuthenticationResponseDto register(Admin admin);
}
