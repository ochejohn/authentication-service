package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;

/**
 * Thrown when an authenticated user
 * is not allowed to perform an operation.
 */
public class ForbiddenException extends BusinessException {

    /**
     * Uses the default message from the ErrorCode.
     */
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Uses a custom message while preserving the ErrorCode.
     */
    public ForbiddenException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}