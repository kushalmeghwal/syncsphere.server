package com.kushal.syncsphere.server.service;

import com.kushal.syncsphere.server.dto.DeviceRequest;
import com.kushal.syncsphere.server.dto.DeviceResponse;
import com.kushal.syncsphere.server.model.Device;
import com.kushal.syncsphere.server.model.User;
import com.kushal.syncsphere.server.repository.DeviceRepository;
import com.kushal.syncsphere.server.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    public DeviceService(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }
    public DeviceResponse registerDevice(DeviceRequest request) {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));

        if(deviceRepository.findByDeviceId(request.getDeviceId()).isPresent()){
                throw new RuntimeException("Device already registered");
        }

        Device device=new Device(request.getDeviceId(),request.getDeviceName(),user);
        Device savedDevice=deviceRepository.save(device);
        return new DeviceResponse(
                savedDevice.getId(),
                savedDevice.getDeviceId(),
                savedDevice.getDeviceName(),
                savedDevice.getUser().getUsername(),
                savedDevice.getLastSeen()
        );
    }
}
