package com.drbooleani.docrecipesapi.web;

import com.drbooleani.docrecipesapi.domain.assets.AssetService;
import com.drbooleani.docrecipesapi.domain.assets.dto.AssetDTO;
import com.drbooleani.docrecipesapi.web.interfaces.GenericCrudController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController implements GenericCrudController<AssetDTO> {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @Override
    public ResponseEntity<List<AssetDTO>> getAll() {
        return ResponseEntity.ok(this.assetService.getAllAssets());
    }

    @Override
    public ResponseEntity<AssetDTO> getById(@PathVariable Long id) {
        AssetDTO assetDTO = this.assetService.getAssetById(id);
        return ResponseEntity.ok(assetDTO);
    }

    @Override
    public ResponseEntity<AssetDTO> create(@Valid @RequestBody AssetDTO assetDTO) {
        AssetDTO response = this.assetService.saveAsset(assetDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<AssetDTO> update(@PathVariable Long id, @RequestBody AssetDTO assetDTO) {
        return ResponseEntity.ok(this.assetService.updateAsset(id, assetDTO));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
