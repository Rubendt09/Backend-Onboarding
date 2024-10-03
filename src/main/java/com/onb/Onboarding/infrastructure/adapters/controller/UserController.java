package com.onb.Onboarding.infrastructure.adapters.controller;

import com.onb.Onboarding.infrastructure.adapters.dto.AuthResponse;
import com.onb.Onboarding.infrastructure.adapters.dto.LoginDTO;
import com.onb.Onboarding.infrastructure.adapters.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import com.onb.Onboarding.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onb.Onboarding.infrastructure.security.JwtTokenProvider;
import com.onb.Onboarding.application.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); // Retorna 200 OK con la lista de usuarios
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            User user = userService.registerUser(userRegisterDTO);
            return new ResponseEntity<>(user, HttpStatus.CREATED); // Retornar 201 CREATED si el registro es exitoso
        } catch (IllegalArgumentException e) {
            // Si el email ya existe, devolver un 400 BAD REQUEST con el mensaje de error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDTO loginDTO){
        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null){
            String token = jwtTokenProvider.generateToken(user.getEmail());
            user.setPassword(null);
            return ResponseEntity.ok(new AuthResponse(user, token));
        } return  ResponseEntity.status(401).build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @Valid @RequestBody UserRegisterDTO userUpdateDTO) {
        try {
            User updatedUser = userService.updateUser(email, userUpdateDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUserByEmail(email);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
