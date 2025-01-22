package com.drbooleani.docrecipesapi.domain.room;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoomInventoryId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "room_id")
    private Long roomId;
}
