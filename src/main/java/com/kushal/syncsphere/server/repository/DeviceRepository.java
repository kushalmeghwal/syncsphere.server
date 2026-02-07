package com.kushal.syncsphere.server.repository;

import com.kushal.syncsphere.server.model.Device;
import com.kushal.syncsphere.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findByDeviceId(String deviceId);
    List<Device> findAllByUserId(User user);
}
