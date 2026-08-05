package com.devops.authentication.common.constant;

/**
 * Centralized API response messages.
 *
 * This class contains all user-facing success and informational
 * messages used throughout the application.
 *
 * Error messages are managed separately through {@code ErrorCode}.
 */
public final class ApiMessages {

    /**
     * Prevent instantiation.
     */
    private ApiMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    // =====================================================
    // Authentication
    // =====================================================

    public static final String LOGIN_SUCCESS =
            "Login successful.";

    public static final String LOGOUT_SUCCESS =
            "Logout successful.";

    public static final String TOKEN_REFRESH_SUCCESS =
            "Token refreshed successfully.";

    public static final String PASSWORD_RESET_EMAIL_SENT =
            "Password reset email has been sent.";

    public static final String PASSWORD_RESET_SUCCESS =
            "Password has been reset successfully.";

    public static final String EMAIL_VERIFIED_SUCCESS =
            "Email verified successfully.";

    // =====================================================
    // User
    // =====================================================

    public static final String USER_REGISTERED_SUCCESS =
            "User registered successfully.";

    public static final String USER_UPDATED_SUCCESS =
            "User updated successfully.";

    public static final String USER_DELETED_SUCCESS =
            "User deleted successfully.";

    public static final String USER_RETRIEVED_SUCCESS =
            "User retrieved successfully.";

    public static final String USERS_RETRIEVED_SUCCESS =
            "Users retrieved successfully.";

    // =====================================================
    // Role
    // =====================================================

    public static final String ROLE_CREATED_SUCCESS =
            "Role created successfully.";

    public static final String ROLE_UPDATED_SUCCESS =
            "Role updated successfully.";

    public static final String ROLE_DELETED_SUCCESS =
            "Role deleted successfully.";

    // =====================================================
    // Permission
    // =====================================================

    public static final String PERMISSION_CREATED_SUCCESS =
            "Permission created successfully.";

    public static final String PERMISSION_UPDATED_SUCCESS =
            "Permission updated successfully.";

    public static final String PERMISSION_DELETED_SUCCESS =
            "Permission deleted successfully.";
}