package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;

/**
 * Thrown when the client sends
 * an invalid request.
 */
public class BadRequestException extends BusinessException {

    /**
     * Uses the default message from the ErrorCode.
     */
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Uses a custom message while preserving the ErrorCode.
     */
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}