package com.alpha.olsp.service;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.request.AuthenticationRequestDto;
import com.alpha.olsp.dto.request.RegisterRequestDto;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.User;
import com.alpha.olsp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponseDto register(RegisterRequestDto registerRequest) {
        //Construct user object from registerRequest
        User user = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role()
        );
        //save the user
        User registeredUser = userRepository.save(user);
        //generate token
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponseDto(token);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        //Now authentication is successfully
        //Next, generate token for this authenticated user
//        Principal principal = authentication.getPrincipal();
        User user = (User)authentication.getPrincipal();
//        User user = (User) userDetailsService.loadUserByUsername(authenticationRequest.username());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponseDto(token);
    }
}
