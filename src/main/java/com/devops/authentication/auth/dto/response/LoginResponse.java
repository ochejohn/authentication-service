package com.devops.authentication.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * Response returned after successful authentication.
 *
 * Contains JWT access token and refresh token information.
 */
@Getter
@Builder
public class LoginResponse {

    /**
     * JWT access token used to access protected endpoints.
     */
    private final String accessToken;

    /**
     * Refresh token used to obtain a new access token.
     */
    private final String refreshToken;

    /**
     * Token type.
     *
     * Example: Bearer
     */
    @Builder.Default
    private final String tokenType = "Bearer";

    /**
     * Access token expiration time.
     */
    private final Instant expiresAt;

}