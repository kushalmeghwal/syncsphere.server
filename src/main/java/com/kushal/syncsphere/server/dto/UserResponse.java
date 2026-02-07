package com.kushal.syncsphere.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String accessToken;
    private String refreshToken;
}
