package com.devops.authentication.security;

import com.devops.authentication.common.api.BaseResponse;
import com.devops.authentication.common.enums.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityExceptionHandler {


    private final ObjectMapper objectMapper;


    public SecurityExceptionHandler(
            ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
    }


    public void sendUnauthorizedResponse(
            HttpServletResponse response,
            ErrorCode errorCode,
            String detail
    ) throws IOException {


        response.setStatus(
                errorCode.getHttpStatus().value()
        );


        response.setContentType(
                "application/json"
        );


        BaseResponse<Void> body =
                BaseResponse.failure(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        List.of(detail)
                );


        response.getWriter()
                .write(
                        objectMapper.writeValueAsString(body)
                );
    }
}