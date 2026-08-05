package com.devops.authentication.permission.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class PermissionResponse {

    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;

}