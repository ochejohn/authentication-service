CREATE TABLE tokens
(
    id BIGINT NOT NULL AUTO_INCREMENT,

    token VARCHAR(512) NOT NULL,
    token_type VARCHAR(20) NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    expired BOOLEAN NOT NULL DEFAULT FALSE,

    expires_at TIMESTAMP NOT NULL,
    last_used_at TIMESTAMP NULL,

    device_name VARCHAR(255),
    ip_address VARCHAR(100),
    user_agent VARCHAR(500),

    user_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_tokens PRIMARY KEY (id),

    CONSTRAINT uk_tokens_token UNIQUE (token),

    CONSTRAINT fk_tokens_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);

CREATE INDEX idx_tokens_user_id
    ON tokens (user_id);

CREATE INDEX idx_tokens_type
    ON tokens (token_type);

CREATE INDEX idx_tokens_revoked
    ON tokens (revoked);

CREATE INDEX idx_tokens_expired
    ON tokens (expired);