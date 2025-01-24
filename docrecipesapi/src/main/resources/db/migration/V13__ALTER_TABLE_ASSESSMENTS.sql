DROP TABLE IF EXISTS assessments;

CREATE TABLE assessments (
    team_member_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    pre_op_a_id VARCHAR(255),
    start_date TIMESTAMP,
    PRIMARY KEY (team_member_id, patient_id),
    CONSTRAINT fk_assessments_team_member_id FOREIGN KEY (team_member_id) REFERENCES team_member(id) ON DELETE SET NULL,
    CONSTRAINT fk_assessments_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    CONSTRAINT fk_assessments_pre_op_a_id FOREIGN KEY (pre_op_a_id) REFERENCES pre_operative_assessments(name) ON DELETE SET NULL
);