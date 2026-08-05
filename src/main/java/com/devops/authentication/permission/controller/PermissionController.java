package com.devops.authentication.permission.controller;

import com.devops.authentication.common.api.BaseResponse;
import com.devops.authentication.permission.dto.request.CreatePermissionRequest;
import com.devops.authentication.permission.dto.request.UpdatePermissionRequest;
import com.devops.authentication.permission.dto.response.PermissionResponse;
import com.devops.authentication.permission.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;


    @PostMapping
    @PreAuthorize("hasAuthority('SYSTEM_CONFIG')")
    public BaseResponse<PermissionResponse> createPermission(
            @Valid @RequestBody CreatePermissionRequest request
    ) {

        PermissionResponse response =
                permissionService.createPermission(request);

        return BaseResponse.success(
                "Permission created successfully.",
                response
        );
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public BaseResponse<List<PermissionResponse>> getAllPermissions() {

        List<PermissionResponse> response =
                permissionService.getAllPermissions();

        return BaseResponse.success(
                "Permissions retrieved successfully.",
                response
        );
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public BaseResponse<PermissionResponse> getPermissionById(
            @PathVariable Long id
    ) {

        PermissionResponse response =
                permissionService.getPermissionById(id);

        return BaseResponse.success(
                "Permission retrieved successfully.",
                response
        );
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SYSTEM_CONFIG')")
    public BaseResponse<PermissionResponse> updatePermission(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePermissionRequest request
    ) {

        PermissionResponse response =
                permissionService.updatePermission(id, request);

        return BaseResponse.success(
                "Permission updated successfully.",
                response
        );
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SYSTEM_CONFIG')")
    public BaseResponse<Void> deletePermission(
            @PathVariable Long id
    ) {

        permissionService.deletePermission(id);

        return BaseResponse.success(
                "Permission deleted successfully.",
                null
        );
    }
}