package com.kushal.syncsphere.server.dto;

import com.kushal.syncsphere.server.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class DeviceResponse {
    private Long id;
    private String DeviceId;
    private String DeviceName;
    private String username;
    private LocalDateTime lastSeen;
}
