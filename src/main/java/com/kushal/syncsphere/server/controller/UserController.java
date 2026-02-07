package com.kushal.syncsphere.server.controller;

import com.kushal.syncsphere.server.dto.UserRequest;
import com.kushal.syncsphere.server.dto.UserResponse;


import com.kushal.syncsphere.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserResponse> refreshToken(@RequestParam String token){
        return ResponseEntity.ok(userService.refreshToken(token));
    }
    @PostMapping("/logout")
    public ResponseEntity<UserResponse> logout(@RequestParam String token){
        return ResponseEntity.ok(userService.logout(token));
    }
}
