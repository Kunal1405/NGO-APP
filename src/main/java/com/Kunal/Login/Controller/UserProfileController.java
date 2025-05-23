package com.Kunal.Login.Controller;

import com.Kunal.Login.Model.Donation;
import com.Kunal.Login.Service.UserProfileService;
import com.Kunal.Login.dto.DonationRequest;
import com.Kunal.Login.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile() {
//        System.out.println(userProfileService.getProfile());
        return ResponseEntity.ok(userProfileService.getProfile());
    }

    @PostMapping("/donate")
    public ResponseEntity<String> donate(@RequestBody DonationRequest request) {
        return ResponseEntity.ok(userProfileService.donate(request));
    }
    @GetMapping("/profile/donations")

    public ResponseEntity<List<Donation>> getDonations() {return ResponseEntity.ok(
            userProfileService.getDonations());} //List<Donation>>
}

