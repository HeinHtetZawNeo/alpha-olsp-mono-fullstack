package com.alpha.olsp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.alpha.olsp.user.Role;
import com.alpha.olsp.user.Permission;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        request ->
                                request.requestMatchers("/api/v1/auth/*").permitAll()
//                                        .requestMatchers("/api/v1/auth/authenticate").permitAll()
                                        .requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
                                        .requestMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(), Role.MEMBER.name())
                                        .requestMatchers("/api/v1/management/member-only").hasAnyAuthority(
                                                Permission.MEMBER_READ.getPermission(),
                                                Permission.MEMBER_WRITE.getPermission()
                                        )
                                        .anyRequest()
                                        .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http. build();
    }
}
