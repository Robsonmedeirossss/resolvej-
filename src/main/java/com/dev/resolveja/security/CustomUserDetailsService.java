package com.dev.resolveja.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.resolveja.domain.User;
import com.dev.resolveja.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        
        User user = this.userRepository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("User not exists")
        );

        return new CustomUserDetails(user);
    }
    
}
