package com.devops.authentication.role.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder
public class RoleResponse {

    private Long id;

    private String name;

    private String description;

    private boolean enabled;

    private Set<String> permissions;

    private Instant createdAt;

    private Instant updatedAt;

}