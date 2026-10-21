package com.dev.resolveja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dev.resolveja.domain.User;
import com.dev.resolveja.dto.mapper.UserMapper;
import com.dev.resolveja.dto.request.RegisterRequest;
import com.dev.resolveja.dto.request.update.UserUpdateRequest;
import com.dev.resolveja.dto.response.RegisterResponse;
import com.dev.resolveja.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<RegisterResponse> findAll(int page, int size, String orderBy, String direction) {
       
       Sort sort = Sort.by(
            Sort.Direction.fromString(direction),
            orderBy
       );
      
       Pageable pageable = PageRequest.of(page, size, sort);

        return this.userRepository.findAll(pageable)
            .stream()
            .map(UserMapper::toResponse)
            .toList();
    }

    public RegisterResponse findById(Long id) {
        return UserMapper.toResponse(
            this.findEntityById(id, "user")
        );
    }

    public RegisterResponse create(RegisterRequest request) {

        if(this.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "E-mail fornecido já está em uso"
            );
        }

        return UserMapper.toResponse(
            this.userRepository.save(
                UserMapper.toEntity(request, this.passwordEncoder.encode(request.password()))
            )
        );
    }

    public RegisterResponse updateById(Long id, UserUpdateRequest request) {
        User user = this.findEntityById(id, "user");

        request.merge(user);

        return UserMapper.toResponse(
            this.userRepository.save(user)
        );
    }

    public void deleteById(Long id) {
        this.findEntityById(id, "user");
        this.userRepository.deleteById(id);
    }

    protected User findEntityById(Long id, String userType) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Nenhum %s com id %d encontrado", userType, id))
            );
    }

    protected Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    protected User save(User newUser) {
        return this.userRepository.save(newUser);
    }
    
}
