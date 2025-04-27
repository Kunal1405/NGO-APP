package com.Kunal.Login.Controller;

import com.Kunal.Login.Configuration.JwtService;
import com.Kunal.Login.Model.User;
import com.Kunal.Login.Service.UserService;
import com.Kunal.Login.dto.AuthResponse;
import com.Kunal.Login.dto.LoginRequest;
import com.Kunal.Login.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return "Signup successful";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
    @GetMapping("/oauth2/success")
    public String oauthSuccess() {
        return "OAuth2 login successful!";
    }

}
