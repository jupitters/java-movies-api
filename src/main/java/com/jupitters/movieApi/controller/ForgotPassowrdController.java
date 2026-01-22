package com.jupitters.movieApi.controller;

import com.jupitters.movieApi.auth.model.ForgotPassword;
import com.jupitters.movieApi.auth.model.User;
import com.jupitters.movieApi.auth.repositories.ForgotPasswordRepository;
import com.jupitters.movieApi.auth.repositories.UserRepository;
import com.jupitters.movieApi.dto.MailBody;
import com.jupitters.movieApi.exception.ResourceNotFoundException;
import com.jupitters.movieApi.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot-password")
public class ForgotPassowrdController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;

    @PostMapping("/{email}/verify")
    public ResponseEntity<String> verifyEmail(@PathVariable String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your forgot password request: " + otp)
                .subject("OTP for forgot password request")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Email sent for verification");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
