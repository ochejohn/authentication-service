package com.devops.authentication.user.controller;

import com.devops.authentication.common.api.BaseResponse;
import com.devops.authentication.common.constant.ApiMessages;
import com.devops.authentication.security.CustomUserDetails;
import com.devops.authentication.user.dto.request.RegisterRequest;
import com.devops.authentication.user.dto.response.UserResponse;
import com.devops.authentication.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserResponse>> registerUser(
            @Valid @RequestBody RegisterRequest request
    ) {

        UserResponse response = userService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        BaseResponse.success(
                                ApiMessages.USER_REGISTERED_SUCCESS,
                                response
                        )
                );
    }


    @GetMapping("/me")
    @PreAuthorize("hasAuthority('PROFILE_VIEW')")
    public ResponseEntity<BaseResponse<UserResponse>> getCurrentUser(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        UserResponse response =
                userService.getCurrentUser(
                        userDetails.getUsername()
                );

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Current user retrieved successfully.",
                        response
                )
        );
    }
}