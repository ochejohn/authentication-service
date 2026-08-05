package com.devops.authentication.common.constant;

public final class ApiConstants {

    private ApiConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String API_V1 = "/api/v1";

    public static final String AUTH = API_V1 + "/auth";
    public static final String USERS = API_V1 + "/users";
    public static final String ROLES = API_V1 + "/roles";
    public static final String PERMISSIONS = API_V1 + "/permissions";
    public static final String TOKENS = API_V1 + "/tokens";
    public static final String AUDIT = API_V1 + "/audit";

}