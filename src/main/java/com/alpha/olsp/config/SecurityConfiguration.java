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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.
//                csrf(CsrfConfigurer::disable)
//                .authorizeHttpRequests(request ->
//                        request.requestMatchers(
//                                        "/api/v1/products/**",
//                                        "/api/v1/catalogs/**",
//                                        "/api/v1/admin/register",
//                                        "/api/v1/seller/register",
//                                        "/api/v1/customer/register",
//                                        "/api/v1/auth/login",
//                                        "/cms/v1/admin/login" // Allow only login
//                                ).permitAll()
//                                .requestMatchers("/cms/v1/admin/**").hasRole("ADMIN") // Restrict other admin endpoints to ADMIN role
//                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // Restrict API admin endpoints
//                                .requestMatchers("/api/v1/seller/**").hasRole("SELLER")
//                                .requestMatchers("/api/v1/customer/**").hasRole("CUSTOMER")
//                                .requestMatchers("/api/v1/orders/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/cms/v1/admin/login")
//                        .defaultSuccessUrl("/cms/v1/admin/dashboard", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/cms/v1/admin/logout")
//                        .logoutSuccessUrl("/cms/v1/admin/login")
//                        .permitAll()
//                )
//                .authenticationProvider(authenticationProvider)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/v1/**")
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/v1/products/**",
                                "/api/v1/catalogs/**",
                                "/api/v1/admin/register",
                                "/api/v1/seller/register",
                                "/api/v1/customer/register",
                                "/api/v1/auth/login"
                        ).permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/seller/**").hasRole("SELLER")
                        .requestMatchers("/api/v1/customer/**").hasRole("CUSTOMER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public SecurityFilterChain cmsSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/cms/v1/**")
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        "/cms/v1/admin/login",
                        "/cms/v1/admin/logout"
                ))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/cms/v1/admin/login").permitAll()
                        .requestMatchers("/cms/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/cms/v1/admin/login")
                        .defaultSuccessUrl("/cms/v1/admin/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/cms/v1/admin/logout")
                        .logoutSuccessUrl("/cms/v1/admin/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
