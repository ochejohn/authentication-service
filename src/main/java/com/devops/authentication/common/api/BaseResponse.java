package com.devops.authentication.common.api;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class BaseResponse<T> {

    @Builder.Default
    private final boolean success = true;

    private final String code;

    private final String message;

    private final T data;

    private final List<String> details;

    @Builder.Default
    private final Instant timestamp = Instant.now();


    public static <T> BaseResponse<T> success(
            String message,
            T data
    ) {

        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }


    public static <T> BaseResponse<T> success(
            T data
    ) {

        return success(
                "Request completed successfully.",
                data
        );
    }


    public static BaseResponse<Void> success(
            String message
    ) {

        return BaseResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();
    }


    public static BaseResponse<Void> failure(
            String code,
            String message,
            List<String> details
    ) {

        return BaseResponse.<Void>builder()
                .success(false)
                .code(code)
                .message(message)
                .details(details)
                .build();
    }
}