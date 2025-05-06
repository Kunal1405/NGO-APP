package com.Kunal.Login.Service;

import com.Kunal.Login.Exception.UserAlreadyExists;
import com.Kunal.Login.Exception.UserNotExists;
import com.Kunal.Login.Exception.WrongLoginCredentials;
import com.Kunal.Login.Model.User;
import com.Kunal.Login.Repositry.UserRepository;
import com.Kunal.Login.dto.LoginRequest;
import com.Kunal.Login.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(SignupRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExists("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProvider("LOCAL");

        userRepository.save(user);
    }

    public User authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new WrongLoginCredentials("Invalid email "));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new WrongLoginCredentials("Invalid  password");
        }

        return user;
    }
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExists("User not found"));

        // FIX: If null, set totalPoints to 0
        if (user.getTotalPoints() == null) {
            user.setTotalPoints(0);
            updateUser(user); // Save the fix to DB
        }
        if (user.getIsAdmin() == null) {
            user.setIsAdmin(false);
            updateUser(user); // Save the fix to DB
        }

        return user;
    }

    public void updateUser(User user) {    // <-- Add this method
        userRepository.save(user);
    }

}
