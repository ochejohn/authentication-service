package com.devops.authentication.role.controller;

import com.devops.authentication.common.api.BaseResponse;
import com.devops.authentication.role.dto.request.CreateRoleRequest;
import com.devops.authentication.role.dto.request.UpdateRoleRequest;
import com.devops.authentication.role.dto.response.RolePermissionResponse;
import com.devops.authentication.role.dto.response.RoleResponse;
import com.devops.authentication.role.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public BaseResponse<RoleResponse> createRole(
            @Valid @RequestBody CreateRoleRequest request
    ) {

        RoleResponse response =
                roleService.createRole(request);

        return BaseResponse.success(
                "Role created successfully.",
                response
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public BaseResponse<List<RoleResponse>> getAllRoles() {

        List<RoleResponse> response =
                roleService.getAllRoles();

        return BaseResponse.success(
                "Roles retrieved successfully.",
                response
        );
    }

    @GetMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public BaseResponse<RoleResponse> getRoleById(
            @PathVariable Long roleId
    ) {

        RoleResponse response =
                roleService.getRoleById(roleId);

        return BaseResponse.success(
                "Role retrieved successfully.",
                response
        );
    }

    @PutMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public BaseResponse<RoleResponse> updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody UpdateRoleRequest request
    ) {

        RoleResponse response =
                roleService.updateRole(
                        roleId,
                        request
                );

        return BaseResponse.success(
                "Role updated successfully.",
                response
        );
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public BaseResponse<Void> deleteRole(
            @PathVariable Long roleId
    ) {

        roleService.deleteRole(roleId);

        return BaseResponse.success(
                "Role deleted successfully.",
                null
        );
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN')")
    public BaseResponse<RolePermissionResponse> assignPermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId
    ) {

        RolePermissionResponse response =
                roleService.assignPermission(
                        roleId,
                        permissionId
                );

        return BaseResponse.success(
                "Permission assigned successfully.",
                response
        );
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE_REMOVE')")
    public BaseResponse<RolePermissionResponse> removePermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId
    ) {

        RolePermissionResponse response =
                roleService.removePermission(
                        roleId,
                        permissionId
                );

        return BaseResponse.success(
                "Permission removed successfully.",
                response
        );
    }

    @GetMapping("/{roleId}/permissions")
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public BaseResponse<RolePermissionResponse> getRolePermissions(
            @PathVariable Long roleId
    ) {

        RolePermissionResponse response =
                roleService.getRolePermissions(roleId);

        return BaseResponse.success(
                "Permissions retrieved successfully.",
                response
        );
    }

}