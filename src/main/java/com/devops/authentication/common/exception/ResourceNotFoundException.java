package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;

/**
 * Thrown when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends BusinessException {

    /**
     * Uses the default message from the ErrorCode.
     */
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Uses a custom message while preserving the ErrorCode.
     */
    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}