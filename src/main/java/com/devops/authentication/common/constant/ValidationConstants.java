package com.devops.authentication.common.constant;

public final class ValidationConstants {

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 100;

    public static final int EMAIL_MIN_LENGTH = 5;
    public static final int EMAIL_MAX_LENGTH = 255;

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 128;

    public static final int PHONE_NUMBER_MIN_LENGTH = 10;
    public static final int PHONE_NUMBER_MAX_LENGTH = 15;

    public static final int ROLE_NAME_MAX_LENGTH = 50;
    public static final int PERMISSION_NAME_MAX_LENGTH = 100;

    public static final String NAME_PATTERN = "^[A-Za-z ]+$";

    public static final String PHONE_NUMBER_PATTERN = "^\\+?[0-9]{10,15}$";

    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,128}$";

}