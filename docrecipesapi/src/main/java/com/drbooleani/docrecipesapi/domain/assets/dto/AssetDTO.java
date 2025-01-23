package com.drbooleani.docrecipesapi.domain.assets.dto;

import com.drbooleani.docrecipesapi.enums.AssetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssetDTO {
    private Long id;

    @NotNull(message = "Asset Type cannot be null")
    private AssetType type;

    @NotNull(message = "Asset Name cannot be null")
    @NotBlank(message = "Asset Name cannot be blank")
    private String name;
}
