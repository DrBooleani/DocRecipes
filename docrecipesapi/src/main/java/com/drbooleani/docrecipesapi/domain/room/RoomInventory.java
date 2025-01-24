package com.drbooleani.docrecipesapi.domain.room;

import com.drbooleani.docrecipesapi.domain._embeddedids.RoomInventoryId;
import com.drbooleani.docrecipesapi.domain.assets.Asset;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "room_inventory")
@NoArgsConstructor
@Getter
@Setter
public class RoomInventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoomInventoryId roomInventoryId;

    @ManyToOne
    @MapsId("assetId")
    @JoinColumn(name = "asset_id", columnDefinition = "BIGINT")
    private Asset asset;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", columnDefinition = "BIGINT")
    private OperationRoom operationRoom;

    private Integer count;
}
