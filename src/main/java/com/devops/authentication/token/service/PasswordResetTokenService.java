package com.devops.authentication.token.service;

import com.devops.authentication.token.entity.PasswordResetToken;
import com.devops.authentication.user.entity.User;


public interface PasswordResetTokenService {


    PasswordResetToken createToken(
            User user
    );


    PasswordResetToken findByToken(
            String token
    );


    boolean isValid(
            PasswordResetToken token
    );


    void markUsed(
            PasswordResetToken token
    );


    void deleteByUser(
            User user
    );

}