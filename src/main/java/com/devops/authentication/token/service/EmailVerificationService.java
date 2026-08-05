package com.devops.authentication.token.service;

import com.devops.authentication.user.entity.User;

public interface EmailVerificationService {

    String createVerificationToken(User user);

    void verifyEmail(String token);

    String resendVerificationToken(User user);
}