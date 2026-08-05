package com.devops.authentication.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AssignRoleRequest {


    @NotBlank(message = "Role name is required.")
    private String role;

}