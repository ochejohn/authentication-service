package com.devops.authentication.token.repository;

import com.devops.authentication.token.entity.EmailVerificationToken;
import com.devops.authentication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenRepository
        extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(
            String token
    );

    Optional<EmailVerificationToken> findByUser(
            User user
    );

    void deleteByUser(
            User user
    );
}