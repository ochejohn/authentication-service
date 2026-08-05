package com.devops.authentication.admin.service.impl;

import com.devops.authentication.admin.dto.response.AdminUserResponse;
import com.devops.authentication.admin.dto.response.AssignRoleResponse;
import com.devops.authentication.admin.service.AdminService;
import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.role.entity.Role;
import com.devops.authentication.role.repository.RoleRepository;
import com.devops.authentication.user.entity.User;
import com.devops.authentication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AdminUserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToAdminUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AdminUserResponse getUserById(
            Long userId
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.USER_001)
                );

        return mapToAdminUserResponse(user);
    }

    @Override
    public AdminUserResponse enableUser(
            Long userId
    ) {

        User user = getUser(userId);

        user.setEnabled(true);

        userRepository.save(user);

        return mapToAdminUserResponse(user);
    }

    @Override
    public AdminUserResponse disableUser(
            Long userId
    ) {

        User user = getUser(userId);

        user.setEnabled(false);

        userRepository.save(user);

        return mapToAdminUserResponse(user);
    }

    @Override
    public AdminUserResponse lockUser(
            Long userId
    ) {

        User user = getUser(userId);

        user.setAccountNonLocked(false);

        userRepository.save(user);

        return mapToAdminUserResponse(user);
    }

    @Override
    public AdminUserResponse unlockUser(
            Long userId
    ) {

        User user = getUser(userId);

        user.setAccountNonLocked(true);

        userRepository.save(user);

        return mapToAdminUserResponse(user);
    }

    @Override
    public void deleteUser(
            Long userId
    ) {

        User user = getUser(userId);

        userRepository.delete(user);
    }

    @Override
    public AssignRoleResponse assignRole(
            Long userId,
            String roleName
    ) {

        User user = getUser(userId);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ROLE_001)
                );

        boolean alreadyAssigned =
                user.getRoles()
                        .stream()
                        .anyMatch(existingRole ->
                                existingRole.getName().equals(roleName)
                        );

        if (!alreadyAssigned) {

            user.getRoles().add(role);

            userRepository.save(user);
        }

        Set<String> roles =
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet());

        return AssignRoleResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    private User getUser(
            Long userId
    ) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.USER_001)
                );
    }

    private AdminUserResponse mapToAdminUserResponse(
            User user
    ) {

        return AdminUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                )
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}