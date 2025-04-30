package com.Kunal.Login.Service;

import com.Kunal.Login.Model.Donation;
import com.Kunal.Login.Model.User;
import com.Kunal.Login.Repositry.DonationRepository;
import com.Kunal.Login.dto.DonationRequest;
import com.Kunal.Login.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final DonationRepository donationRepository;
    private final UserService userService;

    public UserProfileResponse getProfile() {
        User user = getCurrentUser();

        List<Donation> donations = donationRepository.findByUserId(user.getId());

        return new UserProfileResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTotalPoints(),
                donations
        );
    }

    public String donate(DonationRequest request) {
        User user = getCurrentUser();

        int pointsEarned = (int) (request.getDonationAmount() / 10);

        Donation donation = new Donation();
        donation.setUserId(user.getId());
        donation.setDonationAmount(request.getDonationAmount());
        donation.setDonationDate(LocalDateTime.now());
        donation.setPointsEarned(pointsEarned);

        donationRepository.save(donation);

        user.setTotalPoints(user.getTotalPoints() + pointsEarned);
        userService.updateUser(user);

        return "Donation successful! Points earned: " + pointsEarned;
    }

    private User getCurrentUser() {
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return (User)principal;
    }
}
