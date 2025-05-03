package com.Kunal.Login.dto;

import lombok.Data;

@Data
public class DonationRequest {
    private String ngoName;
    private Double donationAmount;
}
