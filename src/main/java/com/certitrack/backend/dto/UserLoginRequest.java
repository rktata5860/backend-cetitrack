package com.certitrack.backend.dto;

import com.certitrack.backend.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    private UserRole role = UserRole.USER;
}
