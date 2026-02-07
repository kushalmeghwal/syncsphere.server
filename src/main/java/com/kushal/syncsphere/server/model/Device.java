package com.kushal.syncsphere.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Table(name="devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private String deviceName;

    @ManyToOne
    @JoinColumn(name="userId",nullable = false)
    private User user;

    private LocalDateTime lastSeen;

    public Device() {

    }

    public Device(String deviceId, String deviceName, User user) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.user = user;
        this.lastSeen = LocalDateTime.now();
    }

}
