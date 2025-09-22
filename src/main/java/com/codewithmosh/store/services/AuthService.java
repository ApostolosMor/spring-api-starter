package com.codewithmosh.store.services;


import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import io.jsonwebtoken.impl.security.EdwardsCurve;
import io.swagger.v3.oas.annotations.callbacks.Callback;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService  {

    private final UserRepository userRepository;
    public User getCurrentUser() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId).orElse(null);
    }
}
