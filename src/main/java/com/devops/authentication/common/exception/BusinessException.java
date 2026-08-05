package com.devops.authentication.common.exception;

import com.devops.authentication.common.enums.ErrorCode;
import lombok.Getter;

/**
 * Custom business exception used throughout the application.
 *
 * Every business exception must be associated with an ErrorCode,
 * which defines the business code, message, and HTTP status.
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    /**
     * Creates a new BusinessException using the default message
     * from the provided ErrorCode.
     *
     * @param errorCode the application error code
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * Creates a new BusinessException with a custom message.
     *
     * @param errorCode the application error code
     * @param message custom error message
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}