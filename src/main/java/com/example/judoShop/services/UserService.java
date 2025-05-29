package com.example.judoShop.services;

import com.example.judoShop.models.User;
import com.example.judoShop.models.enums.Role;
import com.example.judoShop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null){
            return false;
        }

        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // шифрование пароля
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }
}
