package com.devops.authentication.token.service.impl;

import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.notification.service.EmailService;
import com.devops.authentication.token.entity.EmailVerificationToken;
import com.devops.authentication.token.service.EmailVerificationService;
import com.devops.authentication.token.service.EmailVerificationTokenService;
import com.devops.authentication.user.entity.User;
import com.devops.authentication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailVerificationServiceImpl
        implements EmailVerificationService {

    private static final long TOKEN_EXPIRATION_MINUTES = 30;

    private final EmailVerificationTokenService tokenService;

    private final UserRepository userRepository;

    private final EmailService emailService;

    @Value("${app.verification-url:http://localhost:8080/api/auth/verify-email}")
    private String verificationUrl;

    @Override
    public String createVerificationToken(User user) {

        validateUser(user);

        if (user.isEmailVerified()) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        tokenService.deleteByUser(user);

        String token =
                generateAndSaveToken(user);

        String verificationLink =
                verificationUrl + "?token=" + token;

        emailService.sendVerificationEmail(
                user.getEmail(),
                user.getFirstName(),
                verificationLink
        );

        return token;
    }

    @Override
    public void verifyEmail(String token) {

        if (token == null || token.isBlank()) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        EmailVerificationToken verificationToken =
                tokenService.findByToken(token);

        if (verificationToken.isUsed()) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        if (verificationToken.getExpiresAt().isBefore(Instant.now())) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        User user =
                verificationToken.getUser();

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_001);
        }

        if (user.isEmailVerified()) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        user.setEmailVerified(true);

        userRepository.save(user);

        verificationToken.setUsed(true);

        tokenService.save(verificationToken);
    }

    @Override
    public String resendVerificationToken(User user) {

        validateUser(user);

        if (user.isEmailVerified()) {
            throw new BusinessException(ErrorCode.TOKEN_001);
        }

        return createVerificationToken(user);
    }

    private String generateAndSaveToken(User user) {

        String tokenValue =
                UUID.randomUUID().toString();

        EmailVerificationToken verificationToken =
                new EmailVerificationToken();

        verificationToken.setToken(tokenValue);

        verificationToken.setUser(user);

        verificationToken.setExpiresAt(
                Instant.now().plus(
                        TOKEN_EXPIRATION_MINUTES,
                        ChronoUnit.MINUTES
                )
        );

        verificationToken.setUsed(false);

        tokenService.save(
                verificationToken
        );

        return tokenValue;
    }

    private void validateUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User must not be null."
            );
        }
    }
}
