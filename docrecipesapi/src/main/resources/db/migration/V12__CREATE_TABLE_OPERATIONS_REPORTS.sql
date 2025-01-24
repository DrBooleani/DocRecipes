CREATE TABLE operations_reports (
    team_member_id BIGINT NOT NULL,
    operation_id BIGINT NOT NULL,
    report TEXT,
    PRIMARY KEY (team_member_id, operation_id),
    CONSTRAINT fk_operations_reports_team_member_id FOREIGN KEY (team_member_id) REFERENCES team_member(id) ON DELETE CASCADE,
    CONSTRAINT fk_operations_reports_operation_id FOREIGN KEY (operation_id) REFERENCES operations(id) ON DELETE CASCADE
);
