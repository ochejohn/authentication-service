package com.devops.authentication.token.repository;

import com.devops.authentication.token.entity.PasswordResetToken;
import com.devops.authentication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {


    Optional<PasswordResetToken> findByToken(
            String token
    );


    Optional<PasswordResetToken> findByUser(
            User user
    );


    void deleteByUser(
            User user
    );

}