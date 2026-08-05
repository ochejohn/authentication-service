package com.devops.authentication.admin.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;


@Getter
@Builder
public class AssignRoleResponse {


    private Long userId;


    private String email;


    private Set<String> roles;

}