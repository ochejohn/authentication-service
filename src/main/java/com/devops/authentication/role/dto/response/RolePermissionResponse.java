package com.devops.authentication.role.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class RolePermissionResponse {

    private Long roleId;

    private String roleName;

    private Set<String> permissions;

}