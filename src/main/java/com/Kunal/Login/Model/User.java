package com.Kunal.Login.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String provider;
    private Integer totalPoints = 0;

    private  Boolean isAdmin=false;
    // "local" or "google"
}

