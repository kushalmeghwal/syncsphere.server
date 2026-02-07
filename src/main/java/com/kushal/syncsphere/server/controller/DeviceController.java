package com.kushal.syncsphere.server.controller;

import com.kushal.syncsphere.server.dto.DeviceRequest;
import com.kushal.syncsphere.server.dto.DeviceResponse;
import com.kushal.syncsphere.server.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @PostMapping("/register")
    public ResponseEntity<DeviceResponse> registerDevice(@RequestBody DeviceRequest request) {
        return ResponseEntity.ok(deviceService.registerDevice(request));
    }
}
