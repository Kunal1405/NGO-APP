package com.Kunal.Login.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {
    @NotNull(message = "First name cannot be null")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email format")
    private String email;
    private String password;
}

