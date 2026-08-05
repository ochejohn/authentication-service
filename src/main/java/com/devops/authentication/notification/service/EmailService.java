package com.devops.authentication.notification.service;

public interface EmailService {


    void sendVerificationEmail(
            String recipientEmail,
            String firstName,
            String verificationLink
    );


    void sendPasswordResetEmail(
            String recipientEmail,
            String firstName,
            String resetLink
    );

}