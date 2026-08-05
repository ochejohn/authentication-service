package com.devops.authentication.admin.controller;

import com.devops.authentication.admin.dto.request.AssignRoleRequest;
import com.devops.authentication.admin.dto.response.AdminUserResponse;
import com.devops.authentication.admin.dto.response.AssignRoleResponse;
import com.devops.authentication.admin.service.AdminService;
import com.devops.authentication.common.api.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<String> adminTest() {

        return BaseResponse.success(
                "Admin access granted.",
                "ADMIN AREA"
        );
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public BaseResponse<List<AdminUserResponse>> getAllUsers() {

        return BaseResponse.success(
                "Users retrieved successfully.",
                adminService.getAllUsers()
        );
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public BaseResponse<AdminUserResponse> getUserById(
            @PathVariable Long userId
    ) {

        return BaseResponse.success(
                "User retrieved successfully.",
                adminService.getUserById(userId)
        );
    }

    @PutMapping("/users/{userId}/enable")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public BaseResponse<AdminUserResponse> enableUser(
            @PathVariable Long userId
    ) {

        return BaseResponse.success(
                "User enabled successfully.",
                adminService.enableUser(userId)
        );
    }

    @PutMapping("/users/{userId}/disable")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public BaseResponse<AdminUserResponse> disableUser(
            @PathVariable Long userId
    ) {

        return BaseResponse.success(
                "User disabled successfully.",
                adminService.disableUser(userId)
        );
    }

    @PutMapping("/users/{userId}/lock")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public BaseResponse<AdminUserResponse> lockUser(
            @PathVariable Long userId
    ) {

        return BaseResponse.success(
                "User locked successfully.",
                adminService.lockUser(userId)
        );
    }

    @PutMapping("/users/{userId}/unlock")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public BaseResponse<AdminUserResponse> unlockUser(
            @PathVariable Long userId
    ) {

        return BaseResponse.success(
                "User unlocked successfully.",
                adminService.unlockUser(userId)
        );
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public BaseResponse<Void> deleteUser(
            @PathVariable Long userId
    ) {

        adminService.deleteUser(userId);

        return BaseResponse.success(
                "User deleted successfully.",
                null
        );
    }

    @PostMapping("/users/{userId}/roles")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN')")
    public BaseResponse<AssignRoleResponse> assignRole(
            @PathVariable Long userId,
            @Valid @RequestBody AssignRoleRequest request
    ) {

        return BaseResponse.success(
                "Role assigned successfully.",
                adminService.assignRole(
                        userId,
                        request.getRole()
                )
        );
    }

}