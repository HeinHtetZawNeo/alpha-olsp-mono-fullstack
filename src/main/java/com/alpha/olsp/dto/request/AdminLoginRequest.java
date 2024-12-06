package com.alpha.olsp.dto.request;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String password;
}
