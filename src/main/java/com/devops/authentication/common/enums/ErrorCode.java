package com.devops.authentication.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // =====================================================
    // Authentication
    // =====================================================
    AUTH_001("AUTH_001", "Invalid email or password.", HttpStatus.UNAUTHORIZED),
    AUTH_002("AUTH_002", "Invalid token.", HttpStatus.UNAUTHORIZED),
    AUTH_003("AUTH_003", "Access token has expired.", HttpStatus.UNAUTHORIZED),
    AUTH_004("AUTH_004", "Refresh token has expired.", HttpStatus.UNAUTHORIZED),
    AUTH_005("AUTH_005", "Account is disabled.", HttpStatus.FORBIDDEN),
    AUTH_006("AUTH_006", "Account is locked.", HttpStatus.FORBIDDEN),
    AUTH_007("AUTH_007", "Email has not been verified.", HttpStatus.FORBIDDEN),

    // =====================================================
    // User
    // =====================================================
    USER_001("USER_001", "User not found.", HttpStatus.NOT_FOUND),
    USER_002("USER_002", "Email already exists.", HttpStatus.CONFLICT),
    USER_003("USER_003", "Phone number already exists.", HttpStatus.CONFLICT),

    // =====================================================
    // Role
    // =====================================================
    ROLE_001("ROLE_001", "Role not found.", HttpStatus.NOT_FOUND),
    ROLE_002("ROLE_002", "Role already exists.", HttpStatus.CONFLICT),
    ROLE_003("ROLE_003", "Role is disabled.", HttpStatus.BAD_REQUEST),

    // =====================================================
    // Permission
    // =====================================================
    PERMISSION_001("PERMISSION_001", "Permission denied.", HttpStatus.FORBIDDEN),
    PERMISSION_002("PERMISSION_002", "Permission not found.", HttpStatus.NOT_FOUND),
    PERMISSION_003("PERMISSION_003", "Permission already exists.", HttpStatus.CONFLICT),
    PERMISSION_004("PERMISSION_004", "Permission is inactive.", HttpStatus.BAD_REQUEST),

    // =====================================================
    // Token
    // =====================================================
    TOKEN_001("TOKEN_001", "Token not found.", HttpStatus.NOT_FOUND),
    TOKEN_002("TOKEN_002", "Token has been revoked.", HttpStatus.UNAUTHORIZED),

    // =====================================================
    // Validation
    // =====================================================
    VALIDATION_001("VALIDATION_001", "Validation failed.", HttpStatus.BAD_REQUEST),

    // =====================================================
    // System
    // =====================================================
    SYSTEM_001("SYSTEM_001", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM_002("SYSTEM_002", "Bad request.", HttpStatus.BAD_REQUEST);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;
}