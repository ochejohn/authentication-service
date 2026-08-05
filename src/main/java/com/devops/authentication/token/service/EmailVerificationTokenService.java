package com.devops.authentication.token.service;

import com.devops.authentication.token.entity.EmailVerificationToken;
import com.devops.authentication.user.entity.User;

public interface EmailVerificationTokenService {

    EmailVerificationToken save(
            EmailVerificationToken token
    );

    EmailVerificationToken findByToken(
            String token
    );

    void delete(
            EmailVerificationToken token
    );

    void deleteByUser(
            User user
    );
}