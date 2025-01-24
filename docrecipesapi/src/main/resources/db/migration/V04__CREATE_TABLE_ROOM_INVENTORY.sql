CREATE TABLE room_inventory (
    asset_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY (asset_id, room_id),
    CONSTRAINT fk_room_inventory_asset_id FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE,
    CONSTRAINT fk_room_inventory_room_id FOREIGN KEY (room_id) REFERENCES operation_rooms(id) ON DELETE CASCADE
);
