CREATE TABLE operation_type (
    name VARCHAR(255) PRIMARY KEY,
    room_type VARCHAR(255),
    duration_hours INT
);

CREATE TABLE optype_assets (
    optype_id VARCHAR(255) NOT NULL,
    asset_id BIGINT NOT NULL,
    PRIMARY KEY (optype_id, asset_id),
    CONSTRAINT fk_optype_assets_optype_id FOREIGN KEY (optype_id) REFERENCES operation_type(name) ON DELETE CASCADE,
    CONSTRAINT fk_optype_assets_asset_id FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE
);

CREATE TABLE optype_opproviders (
    optype_id VARCHAR(255) NOT NULL,
    opprovider_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (optype_id, opprovider_id),
    CONSTRAINT fk_optype_opproviders_optype_id FOREIGN KEY (optype_id) REFERENCES operation_type(name) ON DELETE CASCADE,
    CONSTRAINT fk_optype_opproviders_opprovider_id FOREIGN KEY (opprovider_id) REFERENCES operation_providers(type) ON DELETE CASCADE
);

CREATE TABLE optype_pre_op_a (
    optype_id VARCHAR(255) NOT NULL,
    pre_op_a_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (optype_id, pre_op_a_id),
    CONSTRAINT fk_optype_pre_op_a_optype_id FOREIGN KEY (optype_id) REFERENCES operation_type(name) ON DELETE CASCADE,
    CONSTRAINT fk_optype_pre_op_a_pre_op_a_id FOREIGN KEY (pre_op_a_id) REFERENCES pre_operative_assessments(name) ON DELETE CASCADE
);


