package com.Kunal.Login.Service;

import com.Kunal.Login.Exception.DonationsNotExists;
import com.Kunal.Login.Exception.UserNotExists;
import com.Kunal.Login.Model.Donation;
import com.Kunal.Login.Model.User;
import com.Kunal.Login.Repositry.DonationRepository;
import com.Kunal.Login.dto.DonationRequest;
import com.Kunal.Login.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final DonationRepository donationRepository;
    private final UserService userService;

    public UserProfileResponse getProfile() {
        User user = getCurrentUser();
        if (user == null) {
            throw new UserNotExists("Unauthorized. Wrong jwt token.");
        }

        List<Donation> donations = donationRepository.findByUserId(user.getId());

        return new UserProfileResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTotalPoints(),
                donations,
                user.getIsAdmin()
        );
    }

    public String donate(DonationRequest request) {
        User user = getCurrentUser();
        if (user == null) {
            throw new UserNotExists("Unauthorized: Wrong jwt token.");
        }

        if (request.getDonationAmount() <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than zero.");
        }

        int pointsEarned = (int) (request.getDonationAmount() / 10);

        Donation donation = new Donation();
        donation.setUserId(user.getId());
        System.out.println("NGO Name: " + request.getNgoName());
        donation.setNgoName(request.getNgoName());
        donation.setDonationAmount(request.getDonationAmount());
        donation.setDonationDate(LocalDateTime.now());
        donation.setPointsEarned(pointsEarned);

        donationRepository.save(donation);

        user.setTotalPoints(user.getTotalPoints() + pointsEarned);
        userService.updateUser(user);

        return "Donation successful! Points earned: " + pointsEarned;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new RuntimeException("Invalid user principal type: " + principal.getClass().getName());
        }
    }

    public List<Donation> getDonations() {
        User user = getCurrentUser();
        if (user == null) {
            throw new UserNotExists("Unauthorized: Wrong jwt token.");
        }
        List<Donation> donations=donationRepository.findByUserId(user.getId());
        if(donations.isEmpty()){
            throw  new DonationsNotExists("You have not done any donations yet");
        }
        return donations;
    }
}
