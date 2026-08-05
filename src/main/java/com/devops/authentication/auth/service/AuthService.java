package com.devops.authentication.auth.service;

import com.devops.authentication.auth.dto.request.LoginRequest;
import com.devops.authentication.auth.dto.request.RefreshTokenRequest;
import com.devops.authentication.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(
            LoginRequest request
    );


    LoginResponse refreshToken(
            RefreshTokenRequest request
    );


    void logout(
            String refreshToken
    );


    /*
     * Email Verification
     */

    void sendVerificationEmail(
            String email
    );


    void verifyEmail(
            String token
    );


    void resendVerificationEmail(
            String email
    );


    /*
     * Password Reset
     */

    void forgotPassword(
            String email
    );


    void resetPassword(
            String token,
            String newPassword
    );


    /*
     * Authenticated User Password Change
     */

    void changePassword(
            Long userId,
            String currentPassword,
            String newPassword
    );

}