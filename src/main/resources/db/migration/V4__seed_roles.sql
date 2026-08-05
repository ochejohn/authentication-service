-- =====================================================
-- Seed Default Roles
-- =====================================================

INSERT INTO roles (
    name,
    description,
    enabled,
    created_at
)
VALUES
    (
        'ROLE_USER',
        'Default application user role',
        TRUE,
        CURRENT_TIMESTAMP
    ),
    (
        'ROLE_ADMIN',
        'Application administrator role',
        TRUE,
        CURRENT_TIMESTAMP
    ),
    (
        'ROLE_SUPER_ADMIN',
        'System super administrator role',
        TRUE,
        CURRENT_TIMESTAMP
    );