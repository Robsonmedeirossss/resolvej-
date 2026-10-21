package com.dev.resolveja.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor 
public class SecurityConfig {
    
    private final SecurityAuthFilter securityAuthFilter;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
       
        httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors((corsConfigurer) -> corsConfigurer.configure(httpSecurity))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers(HttpMethod.POST, "/v1/auth/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v1/services").authenticated()
                    .anyRequest().authenticated();
            })
            .addFilterBefore(this.securityAuthFilter, UsernamePasswordAuthenticationFilter.class)
            ;
       
        return httpSecurity.build();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
