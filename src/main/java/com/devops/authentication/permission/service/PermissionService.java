package com.devops.authentication.permission.service;

import com.devops.authentication.permission.dto.request.CreatePermissionRequest;
import com.devops.authentication.permission.dto.request.UpdatePermissionRequest;
import com.devops.authentication.permission.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {

    PermissionResponse createPermission(
            CreatePermissionRequest request
    );

    PermissionResponse updatePermission(
            Long permissionId,
            UpdatePermissionRequest request
    );

    List<PermissionResponse> getAllPermissions();

    PermissionResponse getPermissionById(
            Long permissionId
    );

    void deletePermission(
            Long permissionId
    );

}