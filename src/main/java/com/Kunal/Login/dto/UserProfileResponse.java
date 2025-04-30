package com.Kunal.Login.dto;

import com.Kunal.Login.Model.Donation;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Integer totalPoints;
    private List<Donation> donations;
}
