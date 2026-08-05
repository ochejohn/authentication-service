package com.devops.authentication.token.repository;

import com.devops.authentication.token.entity.Token;
import com.devops.authentication.token.enums.TokenType;
import com.devops.authentication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * Find a token by its value.
     */
    Optional<Token> findByToken(String token);

    /**
     * Check whether a token already exists.
     */
    boolean existsByToken(String token);

    /**
     * Get every token belonging to a user.
     */
    List<Token> findAllByUser(User user);

    /**
     * Get every active (non-expired, non-revoked) token
     * belonging to a user.
     */
    List<Token> findAllByUserAndRevokedFalseAndExpiredFalse(User user);

    /**
     * Get every active refresh token
     * belonging to a user.
     */
    List<Token> findAllByUserAndTokenTypeAndRevokedFalseAndExpiredFalse(
            User user,
            TokenType tokenType
    );

}