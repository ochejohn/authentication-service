package com.devops.authentication.auth.controller;

import com.devops.authentication.auth.dto.request.*;
import com.devops.authentication.auth.dto.response.LoginResponse;
import com.devops.authentication.auth.service.AuthService;
import com.devops.authentication.common.api.BaseResponse;
import com.devops.authentication.user.entity.User;
import com.devops.authentication.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    private final UserRepository userRepository;



    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Login successful.",
                        response
                )
        );
    }



    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<LoginResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {

        LoginResponse response =
                authService.refreshToken(request);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Token refreshed successfully.",
                        response
                )
        );
    }



    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<Void>> logout(
            @Valid @RequestBody LogoutRequest request
    ) {

        authService.logout(
                request.getRefreshToken()
        );

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Logout successful.",
                        null
                )
        );
    }



    @PostMapping("/send-verification-email")
    public ResponseEntity<BaseResponse<Void>> sendVerificationEmail(
            @RequestParam String email
    ) {

        authService.sendVerificationEmail(email);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Verification email sent successfully.",
                        null
                )
        );
    }



    @GetMapping("/verify-email")
    public ResponseEntity<BaseResponse<Void>> verifyEmail(
            @RequestParam String token
    ) {

        authService.verifyEmail(token);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Email verified successfully.",
                        null
                )
        );
    }



    @PostMapping("/resend-verification-email")
    public ResponseEntity<BaseResponse<Void>> resendVerificationEmail(
            @RequestParam String email
    ) {

        authService.resendVerificationEmail(email);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Verification email resent successfully.",
                        null
                )
        );
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse<Void>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {

        authService.forgotPassword(
                request.getEmail()
        );

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Password reset email sent successfully.",
                        null
                )
        );
    }



    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse<Void>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {

        authService.resetPassword(
                request.getToken(),
                request.getNewPassword()
        );

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Password reset successfully.",
                        null
                )
        );
    }



    @PostMapping("/change-password")
    public ResponseEntity<BaseResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {


        User user =
                userRepository.findByEmail(
                                authentication.getName()
                        )
                        .orElseThrow();



        authService.changePassword(
                user.getId(),
                request.getCurrentPassword(),
                request.getNewPassword()
        );


        return ResponseEntity.ok(
                BaseResponse.success(
                        "Password changed successfully.",
                        null
                )
        );
    }

}