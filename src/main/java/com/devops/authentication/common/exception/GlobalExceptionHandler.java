package com.devops.authentication.common.exception;

import com.devops.authentication.common.api.BaseErrorResponse;
import com.devops.authentication.common.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        BaseErrorResponse response = BaseErrorResponse.error(
                ex.getErrorCode(),
                request.getRequestURI(),
                generateTraceId(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex,
            HttpServletRequest request
    ) {

        BaseErrorResponse response = BaseErrorResponse.error(
                ErrorCode.AUTH_001,
                request.getRequestURI(),
                generateTraceId(),
                "Invalid email or password."
        );

        return ResponseEntity
                .status(ErrorCode.AUTH_001.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .toList();

        BaseErrorResponse response = BaseErrorResponse.error(
                ErrorCode.VALIDATION_001,
                request.getRequestURI(),
                generateTraceId(),
                errors.toArray(new String[0])
        );

        return ResponseEntity
                .status(ErrorCode.VALIDATION_001.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {

        BaseErrorResponse response = BaseErrorResponse.error(
                ErrorCode.PERMISSION_001,
                request.getRequestURI(),
                generateTraceId(),
                "You do not have permission to access this resource."
        );

        return ResponseEntity
                .status(ErrorCode.PERMISSION_001.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        BaseErrorResponse response = BaseErrorResponse.error(
                ErrorCode.SYSTEM_001,
                request.getRequestURI(),
                generateTraceId(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(ErrorCode.SYSTEM_001.getHttpStatus())
                .body(response);
    }

    private String formatFieldError(FieldError error) {

        return error.getField()
                + ": "
                + error.getDefaultMessage();
    }

    private String generateTraceId() {

        return UUID.randomUUID()
                .toString();
    }
}
