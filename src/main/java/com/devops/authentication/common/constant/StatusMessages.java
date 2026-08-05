package com.devops.authentication.common.constant;

import lombok.experimental.UtilityClass;

/**
 * Standard success messages used throughout the application.
 */
@UtilityClass
public class StatusMessages {

    public static final String USER_REGISTERED =
            "User registered successfully.";

    public static final String LOGIN_SUCCESS =
            "Login successful.";

    public static final String LOGOUT_SUCCESS =
            "Logout successful.";

    public static final String PASSWORD_RESET_SUCCESS =
            "Password reset successful.";

    public static final String PASSWORD_CHANGED =
            "Password changed successfully.";

    public static final String TOKEN_REFRESHED =
            "Token refreshed successfully.";

}