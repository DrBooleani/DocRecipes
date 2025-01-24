CREATE TABLE operations (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type_id VARCHAR(255) NOT NULL,
    room_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    state VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_operations_type_id FOREIGN KEY (type_id) REFERENCES operation_type(name) ON DELETE CASCADE,
    CONSTRAINT fk_operations_room_id FOREIGN KEY (room_id) REFERENCES operation_rooms(id) ON DELETE CASCADE,
    CONSTRAINT fk_operations_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE
);

CREATE TABLE optype_team_member (
    operation_id BIGINT NOT NULL,
    team_member_id BIGINT NOT NULL,
    PRIMARY KEY (operation_id, team_member_id),
    CONSTRAINT fk_optype_team_member_operation_id FOREIGN KEY (operation_id) REFERENCES operations(id) ON DELETE CASCADE,
    CONSTRAINT fk_optype_team_member_team_member_id FOREIGN KEY (team_member_id) REFERENCES team_member(id) ON DELETE CASCADE
);
