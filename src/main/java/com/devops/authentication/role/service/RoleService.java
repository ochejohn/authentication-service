package com.devops.authentication.role.service;

import com.devops.authentication.role.dto.request.CreateRoleRequest;
import com.devops.authentication.role.dto.request.UpdateRoleRequest;
import com.devops.authentication.role.dto.response.RolePermissionResponse;
import com.devops.authentication.role.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(
            CreateRoleRequest request
    );

    RoleResponse updateRole(
            Long roleId,
            UpdateRoleRequest request
    );

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(
            Long roleId
    );

    void deleteRole(
            Long roleId
    );

    RolePermissionResponse assignPermission(
            Long roleId,
            Long permissionId
    );

    RolePermissionResponse removePermission(
            Long roleId,
            Long permissionId
    );

    RolePermissionResponse getRolePermissions(
            Long roleId
    );

}