package com.devops.authentication.token.entity;

import com.devops.authentication.common.entity.BaseEntity;
import com.devops.authentication.token.enums.TokenType;
import com.devops.authentication.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TokenType tokenType;

    @Column(nullable = false)
    private boolean revoked = false;

    @Column(nullable = false)
    private boolean expired = false;

    /**
     * Token expiration timestamp.
     */
    @Column(nullable = false)
    private Instant expiresAt;

    /**
     * Last time this token was used.
     */
    @Column
    private Instant lastUsedAt;

    /**
     * Client device name.
     */
    @Column(length = 255)
    private String deviceName;

    /**
     * Client IP address.
     */
    @Column(length = 100)
    private String ipAddress;

    /**
     * Browser / application information.
     */
    @Column(length = 500)
    private String userAgent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}