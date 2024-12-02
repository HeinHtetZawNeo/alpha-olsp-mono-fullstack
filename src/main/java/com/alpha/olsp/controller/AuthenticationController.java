package com.alpha.olsp.controller;

import com.alpha.olsp.dto.request.AuthenticationRequestDto;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.service.AuthenticationService;
import com.alpha.olsp.dto.request.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto registerRequest) {
        AuthenticationResponseDto authenticationResponse = authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        AuthenticationResponseDto authenticationResponse = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
