package com.kushal.syncsphere.server.service;
import com.kushal.syncsphere.server.dto.UserRequest;
import com.kushal.syncsphere.server.model.RefreshToken;
import com.kushal.syncsphere.server.model.User;
import com.kushal.syncsphere.server.dto.UserResponse;
import com.kushal.syncsphere.server.repository.RefreshTokenRepository;
import com.kushal.syncsphere.server.repository.UserRepository;
import com.kushal.syncsphere.server.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    public UserResponse register(UserRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> { throw new RuntimeException("Username already exists"); });
        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
        userRepository.save(user);
        return new UserResponse("User registered Successfullly","go to login page");
    }
    public UserResponse login(UserRequest request) {

        User user=userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new RuntimeException("user does not exists"));


        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
        refreshTokenRepository.save(new RefreshToken(refreshToken,user.getUsername()));
        return new UserResponse(accessToken,refreshToken);
    }
    public UserResponse refreshToken(String token) {
        RefreshToken stored= refreshTokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("Invalid token"));
        if(stored.isRevoked()){
            throw new RuntimeException("Token is already revoked");
        }
        String username = jwtUtil.extractUsername(token);
        String newAccessToken = jwtUtil.generateAccessToken(username);
        return new UserResponse(newAccessToken,token);
    }

    public UserResponse logout(String token) {
        RefreshToken stored = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));
        stored.revoke();
        refreshTokenRepository.save(stored);
        return new UserResponse("user logged out successfully", "go to login page");
    }
}
