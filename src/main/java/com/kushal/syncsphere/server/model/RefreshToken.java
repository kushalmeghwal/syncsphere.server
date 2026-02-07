package com.kushal.syncsphere.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,length = 500)
    private String token;
    @Column(nullable = false)
    private String username;

    private boolean revoked;
    private LocalDateTime createdAt=LocalDateTime.now();

    public RefreshToken(){

    }
    public RefreshToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public void revoke(){
        this.revoked=true;
    }
}
