CREATE TABLE audit_logs (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            user_id BIGINT,
                            action VARCHAR(100) NOT NULL,
                            entity_name VARCHAR(100) NOT NULL,
                            entity_id BIGINT,
                            ip_address VARCHAR(45),
                            user_agent VARCHAR(500),
                            status VARCHAR(50) NOT NULL,
                            details TEXT,
                            created_at TIMESTAMP NOT NULL,
                            updated_at TIMESTAMP NULL,

                            CONSTRAINT pk_audit_logs PRIMARY KEY (id),

                            CONSTRAINT fk_audit_logs_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE SET NULL
);

CREATE INDEX idx_audit_logs_user
    ON audit_logs(user_id);

CREATE INDEX idx_audit_logs_action
    ON audit_logs(action);

CREATE INDEX idx_audit_logs_entity
    ON audit_logs(entity_name, entity_id);

CREATE INDEX idx_audit_logs_created_at
    ON audit_logs(created_at);