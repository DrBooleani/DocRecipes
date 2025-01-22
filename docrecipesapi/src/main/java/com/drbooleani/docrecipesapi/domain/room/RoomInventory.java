package com.drbooleani.docrecipesapi.domain.room;

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
    @MapsId
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "room_id")
    private OperationRoom operationRoom;

    private Integer count;
}
