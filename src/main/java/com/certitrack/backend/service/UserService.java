package com.certitrack.backend.service;

import com.certitrack.backend.dto.UserLoginRequest;
import com.certitrack.backend.dto.UserRegistrationRequest;
import com.certitrack.backend.entity.LoginHistory;
import com.certitrack.backend.entity.User;
import com.certitrack.backend.entity.UserRole;
import com.certitrack.backend.entity.UserStatus;
import com.certitrack.backend.repository.LoginHistoryRepository;
import com.certitrack.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    public User registerUser(UserRegistrationRequest request) {
        UserRole role = request.getRole() == null ? UserRole.USER : request.getRole();

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(User.builder()
                        .email(request.getEmail())
                        .createdAt(LocalDateTime.now())
                        .loginCount(0)
                        .status(UserStatus.ACTIVE)
                        .build());

        user.setName(request.getName());
        user.setRole(role);

        if (user.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        }
        if (user.getLoginCount() == null) {
            user.setLoginCount(0);
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        return userRepository.save(user);
    }

    public User recordLogin(UserLoginRequest request) {
        UserRole role = request.getRole() == null ? UserRole.USER : request.getRole();
        LocalDateTime now = LocalDateTime.now();

        User user = userRepository.findByEmail(request.getEmail())
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setRole(role);
                    existing.setLastLogin(now);
                    existing.setLoginCount(existing.getLoginCount() + 1);
                    if (existing.getStatus() == null) {
                        existing.setStatus(UserStatus.ACTIVE);
                    }
                    return existing;
                })
                .orElse(User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .role(role)
                        .createdAt(now)
                        .lastLogin(now)
                        .loginCount(1)
                        .status(UserStatus.ACTIVE)
                        .build());

        User savedUser = userRepository.save(user);

        LoginHistory loginHistory = LoginHistory.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getName())
                .email(savedUser.getEmail())
                .loginTime(now)
                .build();

        loginHistoryRepository.save(loginHistory);
        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long getRegisteredUserCount() {
        return userRepository.count();
    }

    public List<User> getLoggedInUsers() {
        return userRepository.findByLoginCountGreaterThan(0);
    }

    public long getLoggedInUsersCount() {
        return userRepository.countByLoginCountGreaterThan(0);
    }
}
