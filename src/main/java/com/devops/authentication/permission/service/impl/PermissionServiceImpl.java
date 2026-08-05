package com.devops.authentication.permission.service.impl;

import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.permission.dto.request.CreatePermissionRequest;
import com.devops.authentication.permission.dto.request.UpdatePermissionRequest;
import com.devops.authentication.permission.dto.response.PermissionResponse;
import com.devops.authentication.permission.entity.Permission;
import com.devops.authentication.permission.repository.PermissionRepository;
import com.devops.authentication.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;


    @Override
    public PermissionResponse createPermission(
            CreatePermissionRequest request
    ) {

        if (permissionRepository.existsByName(request.getName())) {
            throw new BusinessException(ErrorCode.PERMISSION_002);
        }

        Permission permission = Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .build();

        Permission savedPermission =
                permissionRepository.save(permission);

        return mapToResponse(savedPermission);
    }


    @Override
    public PermissionResponse updatePermission(
            Long permissionId,
            UpdatePermissionRequest request
    ) {

        Permission permission =
                permissionRepository.findById(permissionId)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.PERMISSION_003
                                )
                        );

        permission.setDescription(
                request.getDescription()
        );

        if (request.getActive() != null) {
            permission.setActive(
                    request.getActive()
            );
        }

        Permission updatedPermission =
                permissionRepository.save(permission);

        return mapToResponse(updatedPermission);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> getAllPermissions() {

        return permissionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public PermissionResponse getPermissionById(
            Long permissionId
    ) {

        Permission permission =
                permissionRepository.findById(permissionId)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.PERMISSION_003
                                )
                        );

        return mapToResponse(permission);
    }


    @Override
    public void deletePermission(
            Long permissionId
    ) {

        Permission permission =
                permissionRepository.findById(permissionId)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.PERMISSION_003
                                )
                        );

        permissionRepository.delete(permission);
    }


    private PermissionResponse mapToResponse(
            Permission permission
    ) {

        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .active(permission.getActive())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .build();
    }

}