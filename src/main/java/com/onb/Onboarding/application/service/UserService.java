package com.onb.Onboarding.application.service;

import com.onb.Onboarding.infrastructure.adapters.dto.UserRegisterDTO;
import com.onb.Onboarding.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.onb.Onboarding.infrastructure.adapters.ports.out.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserRegisterDTO userRegisterDTO) {

        if (userRepository.findByEmail(userRegisterDTO.getEmail()) != null) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        User newUser = new User();
        newUser.setName(userRegisterDTO.getName());
        newUser.setLastname(userRegisterDTO.getLastname());
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setRol(userRegisterDTO.getRol());
        newUser.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));

        return userRepository.save(newUser);
    }

    public  User login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user != null && bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }return null;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            user.setPassword(null);
            return user;
        }).collect(Collectors.toList());
    }

    public User updateUser(String email, UserRegisterDTO userUpdateDTO) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        existingUser.setName(userUpdateDTO.getName());
        existingUser.setLastname(userUpdateDTO.getLastname());
        existingUser.setRol(userUpdateDTO.getRol());

        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
            existingUser.setPassword(bCryptPasswordEncoder.encode(userUpdateDTO.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new IllegalArgumentException("El usuario con el email " + email + " no existe.");
        }
    }
}