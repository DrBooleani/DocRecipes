package com.drbooleani.docrecipesapi.domain.inventory.dto;

import com.drbooleani.docrecipesapi.domain.assets.dto.AssetDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDTO {

    private Long assetId;

    @Valid
    private AssetDTO asset;

    @Min(value = 1, message = "Count must be at least 1")
    private Integer count;
}
