package com.drbooleani.docrecipesapi.domain.room.dto;

import com.drbooleani.docrecipesapi.domain.assets.dto.AssetDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomInventoryDTO {
    @NotNull(message = "AssetId cannot be null")
    private Long assetId;

    @NotNull(message = "OperationRoomId cannot be null")
    private Long operationRoomId;

    private AssetDTO asset;

    private OperationRoomDTO operationRoom;

    @NotNull(message = "Count cannot be null")
    private Integer count;

}
