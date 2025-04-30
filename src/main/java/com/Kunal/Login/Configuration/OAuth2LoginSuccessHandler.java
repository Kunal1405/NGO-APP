package com.Kunal.Login.Configuration;

import com.Kunal.Login.Model.User;
import com.Kunal.Login.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    @Lazy
    private final UserService userService;


    public OAuth2LoginSuccessHandler(JwtService jwtService, @Lazy UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    } // if you want to save user in DB

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user;

        try {
            user = userService.getUserByEmail(email);
            if (user.getTotalPoints() == null) {
                user.setTotalPoints(0);
                userService.updateUser(user);
            }

        } catch (RuntimeException e) {
            // User not found, create a new User
            user = new User();
            user.setEmail(email);
            user.setPassword(""); // no password for Google users
            user.setProvider("GOOGLE"); // or however you want to identify it
            user.setTotalPoints(0);
            userService.updateUser(user); // save the new user
        }

        String jwtToken = jwtService.generateToken(user);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + jwtToken + "\"}");
        response.getWriter().flush();
    }

}

