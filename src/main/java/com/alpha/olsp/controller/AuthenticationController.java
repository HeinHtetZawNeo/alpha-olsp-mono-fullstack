package com.alpha.olsp.controller;

import com.alpha.olsp.dto.request.AuthenticationRequestDto;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Admin;
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

    @PostMapping("/a/register")
    public ResponseEntity<AuthenticationResponseDto> adminRegister(@RequestBody Admin adminRequest) {
        AuthenticationResponseDto authenticationResponse = authenticationService.adminRegister(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
//        AuthenticationResponseDto authenticationResponse = authenticationService.authenticate(authenticationRequest);
//        return ResponseEntity.ok(authenticationResponse);
//    }
}
