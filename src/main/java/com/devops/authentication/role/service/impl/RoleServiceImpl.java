package com.devops.authentication.role.service.impl;

import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.permission.entity.Permission;
import com.devops.authentication.permission.repository.PermissionRepository;
import com.devops.authentication.role.dto.request.CreateRoleRequest;
import com.devops.authentication.role.dto.request.UpdateRoleRequest;
import com.devops.authentication.role.dto.response.RolePermissionResponse;
import com.devops.authentication.role.dto.response.RoleResponse;
import com.devops.authentication.role.entity.Role;
import com.devops.authentication.role.repository.RoleRepository;
import com.devops.authentication.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(
            CreateRoleRequest request
    ) {

        if (roleRepository.existsByName(request.getName())) {
            throw new BusinessException(ErrorCode.ROLE_002);
        }

        Role role = new Role();

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setEnabled(request.isEnabled());

        Role savedRole = roleRepository.save(role);

        return mapToResponse(savedRole);
    }

    @Override
    public RoleResponse updateRole(
            Long roleId,
            UpdateRoleRequest request
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        role.setDescription(request.getDescription());
        role.setEnabled(request.isEnabled());

        Role updatedRole = roleRepository.save(role);

        return mapToResponse(updatedRole);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRoleById(
            Long roleId
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        return mapToResponse(role);
    }

    @Override
    public void deleteRole(
            Long roleId
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        roleRepository.delete(role);
    }

    @Override
    public RolePermissionResponse assignPermission(
            Long roleId,
            Long permissionId
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PERMISSION_002)
                );

        role.getPermissions().add(permission);

        roleRepository.save(role);

        return mapPermissionResponse(role);
    }

    @Override
    public RolePermissionResponse removePermission(
            Long roleId,
            Long permissionId
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.PERMISSION_002)
                );

        role.getPermissions().remove(permission);

        roleRepository.save(role);

        return mapPermissionResponse(role);
    }

    @Override
    @Transactional(readOnly = true)
    public RolePermissionResponse getRolePermissions(
            Long roleId
    ) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        return mapPermissionResponse(role);
    }

    private RoleResponse mapToResponse(
            Role role
    ) {

        Set<String> permissions =
                role.getPermissions() == null
                        ? Collections.emptySet()
                        : role.getPermissions()
                        .stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet());

        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .enabled(role.isEnabled())
                .permissions(permissions)
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }

    private RolePermissionResponse mapPermissionResponse(
            Role role
    ) {

        return RolePermissionResponse.builder()
                .roleId(role.getId())
                .roleName(role.getName())
                .permissions(
                        role.getPermissions()
                                .stream()
                                .map(Permission::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }

}