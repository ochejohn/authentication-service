package com.devops.authentication.auth.service.impl;

import com.devops.authentication.auth.dto.request.LoginRequest;
import com.devops.authentication.auth.dto.request.RefreshTokenRequest;
import com.devops.authentication.auth.dto.response.LoginResponse;
import com.devops.authentication.auth.service.AuthService;
import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.notification.service.EmailService;
import com.devops.authentication.security.JwtService;
import com.devops.authentication.token.entity.EmailVerificationToken;
import com.devops.authentication.token.entity.PasswordResetToken;
import com.devops.authentication.token.entity.Token;
import com.devops.authentication.token.service.EmailVerificationTokenService;
import com.devops.authentication.token.service.PasswordResetTokenService;
import com.devops.authentication.token.service.TokenService;
import com.devops.authentication.user.entity.User;
import com.devops.authentication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final EmailVerificationTokenService verificationTokenService;

    private final PasswordResetTokenService passwordResetTokenService;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;


    @Value("${app.frontend-url}")
    private String frontendUrl;



    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        User user =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.USER_001
                                )
                        );


        String accessToken =
                jwtService.generateToken(
                        user.getEmail()
                );


        Token refreshToken =
                tokenService.createRefreshToken(user);


        Date expiration =
                jwtService.getExpirationDate();


        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .expiresAt(expiration.toInstant())
                .build();
    }




    @Override
    public LoginResponse refreshToken(
            RefreshTokenRequest request
    ) {


        Token oldToken =
                tokenService.findByToken(
                        request.getRefreshToken()
                );


        tokenService.isTokenValid(oldToken);



        Token newRefreshToken =
                tokenService.rotateRefreshToken(
                        oldToken
                );



        User user =
                oldToken.getUser();



        String accessToken =
                jwtService.generateToken(
                        user.getEmail()
                );



        Date expiration =
                jwtService.getExpirationDate();



        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(
                        newRefreshToken.getToken()
                )
                .tokenType("Bearer")
                .expiresAt(
                        expiration.toInstant()
                )
                .build();
    }




    @Override
    public void logout(
            String refreshToken
    ) {

        Token token =
                tokenService.findByToken(refreshToken);


        tokenService.revokeToken(token);
    }






    // ==========================
    // EMAIL VERIFICATION
    // ==========================


    @Override
    public void sendVerificationEmail(
            String email
    ) {


        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.USER_001
                                )
                        );



        if(user.isEmailVerified()){
            return;
        }



        verificationTokenService.deleteByUser(user);



        EmailVerificationToken token =
                new EmailVerificationToken();



        token.setToken(
                UUID.randomUUID().toString()
        );


        token.setUser(user);


        token.setUsed(false);


        token.setExpiresAt(
                Instant.now()
                        .plusSeconds(86400)
        );



        verificationTokenService.save(token);



        String link =
                frontendUrl
                        + "/verify-email?token="
                        + token.getToken();



        emailService.sendVerificationEmail(
                user.getEmail(),
                user.getFirstName(),
                link
        );

    }





    @Override
    public void verifyEmail(
            String tokenValue
    ) {


        EmailVerificationToken token =
                verificationTokenService.findByToken(
                        tokenValue
                );



        if(token.isUsed()
                || token.getExpiresAt()
                .isBefore(Instant.now())){


            throw new BusinessException(
                    ErrorCode.TOKEN_002
            );
        }



        User user =
                token.getUser();



        user.setEmailVerified(true);

        userRepository.save(user);



        token.setUsed(true);

        verificationTokenService.save(token);

    }






    @Override
    public void resendVerificationEmail(
            String email
    ) {

        sendVerificationEmail(email);

    }






    // ==========================
    // FORGOT PASSWORD
    // ==========================


    @Override
    public void forgotPassword(
            String email
    ) {


        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.USER_001
                                )
                        );



        passwordResetTokenService.deleteByUser(user);



        PasswordResetToken token =
                passwordResetTokenService.createToken(user);



        String resetLink =
                frontendUrl
                        + "/reset-password?token="
                        + token.getToken();



        emailService.sendPasswordResetEmail(
                user.getEmail(),
                user.getFirstName(),
                resetLink
        );

    }






    // ==========================
    // RESET PASSWORD
    // ==========================


    @Override
    public void resetPassword(
            String tokenValue,
            String newPassword
    ) {


        PasswordResetToken token =
                passwordResetTokenService.findByToken(
                        tokenValue
                );



        if(!passwordResetTokenService.isValid(token)){

            throw new BusinessException(
                    ErrorCode.TOKEN_002
            );
        }



        User user =
                token.getUser();



        user.setPassword(
                passwordEncoder.encode(
                        newPassword
                )
        );



        userRepository.save(user);



        passwordResetTokenService.markUsed(token);



        tokenService.revokeAllUserTokens(user);

    }






    // ==========================
    // CHANGE PASSWORD
    // ==========================


    @Override
    public void changePassword(
            Long userId,
            String currentPassword,
            String newPassword
    ) {


        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.USER_001
                                )
                        );



        if(!passwordEncoder.matches(
                currentPassword,
                user.getPassword()
        )){


            throw new BusinessException(
                    ErrorCode.AUTH_001
            );
        }



        user.setPassword(
                passwordEncoder.encode(
                        newPassword
                )
        );



        userRepository.save(user);



        tokenService.revokeAllUserTokens(user);

    }

}