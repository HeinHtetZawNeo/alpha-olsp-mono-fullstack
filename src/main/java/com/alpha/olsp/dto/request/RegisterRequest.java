package com.alpha.olsp.dto.request;

import com.alpha.olsp.model.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {}