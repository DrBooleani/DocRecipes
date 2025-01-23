package com.drbooleani.docrecipesapi.domain.assets;

import com.drbooleani.docrecipesapi.config.GenericCrudService;
import com.drbooleani.docrecipesapi.domain.assets.dto.AssetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final GenericCrudService<Asset, AssetDTO> genericCrudService;

    @Autowired
    public AssetService(GenericCrudService<Asset, AssetDTO> genericCrudService) {
        this.genericCrudService = genericCrudService;
    }

    public List<AssetDTO> getAllAssets() {
        return this.genericCrudService.getAll(AssetDTO.class);
    }

    public AssetDTO getAssetById(Long id) {
        return this.genericCrudService.getById(id, Asset.class, AssetDTO.class);
    }

    public AssetDTO saveAsset(AssetDTO assetDTO) {
        return this.genericCrudService.save(assetDTO, Asset.class, AssetDTO.class);
    }

    public AssetDTO updateAsset(Long id, AssetDTO assetDTO) {
        return this.genericCrudService.update(id, assetDTO, Asset.class, AssetDTO.class);
    }

    public void deleteAsset(Long id) {
        this.genericCrudService.delete(id, Asset.class);
    }
}
