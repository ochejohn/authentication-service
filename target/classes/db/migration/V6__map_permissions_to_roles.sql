INSERT INTO role_permissions
(
    role_id,
    permission_id
)

SELECT
    r.id,
    p.id

FROM roles r

         JOIN permissions p

WHERE

    (
        r.name = 'ROLE_USER'
            AND p.name IN
                (
                 'PROFILE_VIEW',
                 'PROFILE_UPDATE'
                    )
        )

   OR

    (
        r.name = 'ROLE_ADMIN'
            AND p.name IN
                (
                 'USER_VIEW',
                 'USER_CREATE',
                 'USER_UPDATE',
                 'USER_DELETE',
                 'ROLE_ASSIGN'
                    )
        )

   OR

    (
        r.name = 'ROLE_SUPER_ADMIN'
            AND p.name IN
                (
                 'SYSTEM_CONFIG',
                 'ROLE_ASSIGN',
                 'ROLE_REMOVE'
                    )
        );