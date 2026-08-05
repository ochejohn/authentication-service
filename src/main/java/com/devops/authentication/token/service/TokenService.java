package com.devops.authentication.token.service;

import com.devops.authentication.token.entity.Token;
import com.devops.authentication.user.entity.User;

public interface TokenService {


    Token createRefreshToken(
            User user
    );


    Token findByToken(
            String token
    );


    boolean isTokenValid(
            Token token
    );


    void revokeToken(
            Token token
    );


    void revokeAllUserTokens(
            User user
    );


    Token rotateRefreshToken(
            Token oldToken
    );

}