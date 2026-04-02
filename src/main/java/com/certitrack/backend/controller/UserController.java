package com.certitrack.backend.controller;

import com.certitrack.backend.dto.ApiResponse;
import com.certitrack.backend.dto.UserLoginRequest;
import com.certitrack.backend.dto.UserRegistrationRequest;
import com.certitrack.backend.entity.User;
import com.certitrack.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok(new ApiResponse<>("User registered successfully", user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> loginUser(@Valid @RequestBody UserLoginRequest request) {
        User user = userService.recordLogin(request);
        return ResponseEntity.ok(new ApiResponse<>("Login tracked successfully", user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getRegisteredUserCount() {
        return ResponseEntity.ok(new ApiResponse<>("Total registered users", userService.getRegisteredUserCount()));
    }

    @GetMapping("/logged-in")
    public ResponseEntity<List<User>> getLoggedInUsers() {
        return ResponseEntity.ok(userService.getLoggedInUsers());
    }
}
