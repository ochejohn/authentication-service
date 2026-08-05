package com.devops.authentication.common.api;

import com.devops.authentication.common.enums.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * Standard API error response.
 *
 * Every exception thrown by the application is converted into this
 * response by the GlobalExceptionHandler to provide a consistent
 * error format across the application.
 */
@Getter
@Builder
public class BaseErrorResponse {

    /**
     * Indicates whether the request was successful.
     * Always false for error responses.
     */
    @Builder.Default
    private final boolean success = false;

    /**
     * Application-specific error code.
     */
    private final ErrorCode code;

    /**
     * Human-readable error message.
     */
    private final String message;

    /**
     * Additional error details.
     *
     * Used mainly for validation errors or
     * business rule violations.
     */
    private final String[] details;

    /**
     * UTC timestamp when the error occurred.
     */
    @Builder.Default
    private final Instant timestamp = Instant.now();

    /**
     * Request URI.
     */
    private final String path;

    /**
     * Correlation ID for tracing requests.
     */
    private final String traceId;

    /**
     * Factory method for creating standardized error responses.
     *
     * The error message is obtained directly from the ErrorCode,
     * making the ErrorCode the single source of truth.
     *
     * @param code application error code
     * @param path request URI
     * @param traceId request correlation ID
     * @param details additional error details
     * @return standardized error response
     */
    public static BaseErrorResponse error(
            ErrorCode code,
            String path,
            String traceId,
            String... details
    ) {

        return BaseErrorResponse.builder()
                .code(code)
                .message(code.getMessage())
                .details(details)
                .path(path)
                .traceId(traceId)
                .build();
    }

}