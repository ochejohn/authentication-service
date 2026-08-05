package com.devops.authentication.admin.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder
public class AdminUserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean enabled;

    private Boolean emailVerified;

    private Set<String> roles;

    private Instant createdAt;

    private Instant updatedAt;

}