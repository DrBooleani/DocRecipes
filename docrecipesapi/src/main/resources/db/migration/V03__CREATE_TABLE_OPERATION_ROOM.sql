CREATE TABLE operation_rooms (
    id BIGSERIAL PRIMARY KEY,
    room_nr INT NOT NULL,
    building_block VARCHAR(255) NOT NULL,
    floor VARCHAR(255),
    type VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL
);
