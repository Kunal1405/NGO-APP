package com.Kunal.Login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationResponse {
    private String message;
    private int pointsEarned;
}
