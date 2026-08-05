package com.devops.authentication.admin.service;

import com.devops.authentication.admin.dto.response.AdminUserResponse;
import com.devops.authentication.admin.dto.response.AssignRoleResponse;

import java.util.List;

public interface AdminService {

    List<AdminUserResponse> getAllUsers();

    AdminUserResponse getUserById(
            Long userId
    );

    AdminUserResponse enableUser(
            Long userId
    );

    AdminUserResponse disableUser(
            Long userId
    );

    AdminUserResponse lockUser(
            Long userId
    );

    AdminUserResponse unlockUser(
            Long userId
    );

    void deleteUser(
            Long userId
    );

    AssignRoleResponse assignRole(
            Long userId,
            String roleName
    );

}