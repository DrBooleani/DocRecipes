CREATE TABLE inventory (
    asset_id BIGINT PRIMARY KEY,
    count INT NOT NULL,
    CONSTRAINT fk_inventory_asset_id FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE
);