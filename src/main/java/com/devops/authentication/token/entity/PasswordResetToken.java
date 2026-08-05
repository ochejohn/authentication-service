package com.devops.authentication.token.entity;

import com.devops.authentication.common.entity.BaseEntity;
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
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BaseEntity {


    @Column(
            nullable = false,
            unique = true,
            length = 255
    )
    private String token;


    @Column(
            nullable = false
    )
    private Instant expiresAt;


    @Column(
            nullable = false
    )
    private boolean used = false;


    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

}