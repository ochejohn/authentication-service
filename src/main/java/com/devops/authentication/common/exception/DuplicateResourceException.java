package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;

/**
 * Thrown when attempting to create a resource
 * that already exists.
 */
public class DuplicateResourceException extends BusinessException {

    /**
     * Uses the default message from ErrorCode.
     *
     * Example:
     * USER_002 -> "Email already exists."
     */
    public DuplicateResourceException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Allows overriding the default message.
     */
    public DuplicateResourceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}