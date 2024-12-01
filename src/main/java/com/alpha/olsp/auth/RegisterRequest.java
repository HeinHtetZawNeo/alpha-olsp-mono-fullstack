package com.alpha.olsp.auth;

import com.alpha.olsp.user.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {}