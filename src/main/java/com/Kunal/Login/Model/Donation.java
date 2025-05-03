package com.Kunal.Login.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String NgoName;
    private Double donationAmount;
    private LocalDateTime donationDate;
    private Integer pointsEarned;
}

