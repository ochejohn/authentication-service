-- =====================================================
-- Add Role Management Permissions
-- =====================================================

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
        'ROLE_CREATE',
        'Create roles',
        TRUE,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        'ROLE_UPDATE',
        'Update roles',
        TRUE,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        'ROLE_DELETE',
        'Delete roles',
        TRUE,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );


-- =====================================================
-- Grant Role Management Permissions to ROLE_ADMIN
-- =====================================================

INSERT INTO role_permissions
(
    role_id,
    permission_id
)
SELECT
    r.id,
    p.id
FROM roles r
         CROSS JOIN permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND p.name IN
      (
       'ROLE_VIEW',
       'ROLE_CREATE',
       'ROLE_UPDATE',
       'ROLE_DELETE',
       'ROLE_ASSIGN',
       'ROLE_REMOVE'
          )
  AND NOT EXISTS
    (
        SELECT 1
        FROM role_permissions rp
        WHERE rp.role_id = r.id
          AND rp.permission_id = p.id
    );