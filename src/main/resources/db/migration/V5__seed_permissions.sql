INSERT INTO permissions
(
    name,
    description,
    active,
    created_at,
    updated_at
)
VALUES

    (
        'USER_VIEW',
        'View users',
        true,
        NOW(),
        NOW()
    ),

    (
        'USER_CREATE',
        'Create users',
        true,
        NOW(),
        NOW()
    ),

    (
        'USER_UPDATE',
        'Update users',
        true,
        NOW(),
        NOW()
    ),

    (
        'USER_DELETE',
        'Delete users',
        true,
        NOW(),
        NOW()
    ),

    (
        'ROLE_VIEW',
        'View roles',
        true,
        NOW(),
        NOW()
    ),

    (
        'ROLE_ASSIGN',
        'Assign roles to users',
        true,
        NOW(),
        NOW()
    ),

    (
        'ROLE_REMOVE',
        'Remove roles from users',
        true,
        NOW(),
        NOW()
    ),

    (
        'PROFILE_VIEW',
        'View own profile',
        true,
        NOW(),
        NOW()
    ),

    (
        'PROFILE_UPDATE',
        'Update own profile',
        true,
        NOW(),
        NOW()
    ),

    (
        'SYSTEM_CONFIG',
        'Manage system configuration',
        true,
        NOW(),
        NOW()
    );