package com.kushal.syncsphere.server.dto;

import com.kushal.syncsphere.server.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceRequest {
    private String DeviceId;
    private String DeviceName;
}
