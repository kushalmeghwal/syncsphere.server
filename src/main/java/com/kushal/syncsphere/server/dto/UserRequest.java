package com.kushal.syncsphere.server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
