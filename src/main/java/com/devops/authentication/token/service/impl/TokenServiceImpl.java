package com.devops.authentication.token.service.impl;

import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.token.entity.Token;
import com.devops.authentication.token.enums.TokenType;
import com.devops.authentication.token.repository.TokenRepository;
import com.devops.authentication.token.service.TokenService;
import com.devops.authentication.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public Token createRefreshToken(User user) {

        Token token = new Token();

        token.setToken(
                generateToken()
        );

        token.setTokenType(
                TokenType.REFRESH
        );

        token.setUser(user);

        token.setExpired(false);

        token.setRevoked(false);

        token.setExpiresAt(
                Instant.now()
                        .plusMillis(refreshTokenExpiration)
        );

        return tokenRepository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public Token findByToken(String token) {

        return tokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new BusinessException(
                                ErrorCode.TOKEN_001
                        )
                );
    }

    @Override
    public boolean isTokenValid(Token token) {

        if (token == null) {
            throw new BusinessException(
                    ErrorCode.TOKEN_001
            );
        }

        if (token.isRevoked()) {
            throw new BusinessException(
                    ErrorCode.TOKEN_002
            );
        }

        if (token.isExpired()) {
            throw new BusinessException(
                    ErrorCode.AUTH_004
            );
        }

        if (token.getExpiresAt() == null) {
            throw new BusinessException(
                    ErrorCode.TOKEN_001
            );
        }

        if (token.getExpiresAt().isBefore(Instant.now())) {

            token.setExpired(true);

            tokenRepository.save(token);

            throw new BusinessException(
                    ErrorCode.AUTH_004
            );
        }

        return true;
    }

    @Override
    public void revokeToken(Token token) {

        token.setRevoked(true);

        token.setExpired(true);

        token.setLastUsedAt(
                Instant.now()
        );

        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {

        List<Token> activeTokens =
                tokenRepository
                        .findAllByUserAndRevokedFalseAndExpiredFalse(
                                user
                        );

        if (activeTokens.isEmpty()) {
            return;
        }

        activeTokens.forEach(token -> {

            token.setRevoked(true);

            token.setExpired(true);

            token.setLastUsedAt(
                    Instant.now()
            );
        });

        tokenRepository.saveAll(activeTokens);
    }

    @Override
    public Token rotateRefreshToken(Token oldToken) {

        revokeToken(oldToken);

        return createRefreshToken(
                oldToken.getUser()
        );
    }

    private String generateToken() {

        return UUID.randomUUID()
                .toString();
    }
}