package com.devops.authentication.common.constant;

/**
 * Application-wide constants.
 *
 * <p>
 * This class contains values that are shared across the entire
 * Authentication Service and should not be hardcoded elsewhere.
 * </p>
 */
public final class ApplicationConstants {

    /**
     * Prevent instantiation.
     */
    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Application name.
     */
    public static final String APPLICATION_NAME = "Authentication Service";

    /**
     * API version.
     */
    public static final String API_VERSION = "v1";

    /**
     * Default success message.
     */
    public static final String SUCCESS_MESSAGE = "Request completed successfully.";

    /**
     * Default error message.
     */
    public static final String ERROR_MESSAGE = "An unexpected error occurred.";

    /**
     * Default page number for pagination.
     */
    public static final int DEFAULT_PAGE_NUMBER = 0;

    /**
     * Default page size.
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * Maximum allowed page size.
     */
    public static final int MAX_PAGE_SIZE = 100;

    /**
     * Default character encoding.
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * UTC time zone.
     */
    public static final String DEFAULT_TIMEZONE = "UTC";

}