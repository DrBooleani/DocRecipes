CREATE TABLE team_member (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    opprovider_id VARCHAR(255),
    CONSTRAINT fk_team_member_opprovider_id FOREIGN KEY (opprovider_id) REFERENCES operation_providers(type) ON DELETE SET NULL
);
