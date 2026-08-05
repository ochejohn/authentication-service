package com.devops.authentication.permission.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePermissionRequest {

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;

    private Boolean active;

}