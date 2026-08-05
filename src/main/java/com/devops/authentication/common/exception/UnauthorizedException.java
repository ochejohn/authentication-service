package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;

/**
 * Thrown when authentication fails.
 */
public class UnauthorizedException extends BusinessException {

    /**
     * Uses the default message from the ErrorCode.
     */
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Uses a custom message while preserving the ErrorCode.
     */
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}