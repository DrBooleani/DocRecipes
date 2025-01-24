package com.drbooleani.docrecipesapi.domain.inventory;

import com.drbooleani.docrecipesapi.domain.assets.Asset;
import com.drbooleani.docrecipesapi.domain.assets.AssetRepository;
import com.drbooleani.docrecipesapi.domain.assets.dto.AssetDTO;
import com.drbooleani.docrecipesapi.domain.inventory.dto.InventoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public InventoryService(InventoryRepository inventoryRepository, AssetRepository assetRepository, ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.assetRepository = assetRepository;
        this.modelMapper = modelMapper;
    }

    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Asset asset = assetRepository.findById(inventoryDTO.getAssetId())
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

        Inventory inventory = new Inventory();
        inventory.setAsset(asset);
        inventory.setCount(inventoryDTO.getCount());

        inventory = inventoryRepository.save(inventory);
        return new InventoryDTO(inventory.getAssetId(),
                new AssetDTO(asset.getId(), asset.getType(), asset.getName()),
                inventory.getCount());
    }

    public Optional<InventoryDTO> updateInventory(Long id, InventoryDTO inventoryDTO) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventory.setCount(inventoryDTO.getCount());
            inventoryRepository.save(inventory);
            return new InventoryDTO(inventory.getAssetId(),
                    new AssetDTO(inventory.getAsset().getId(), inventory.getAsset().getType(), inventory.getAsset().getName()),
                    inventory.getCount());
        });
    }

    public List<InventoryDTO> getAllInventories() {
        List<InventoryDTO> list = new ArrayList<>();
        inventoryRepository.findAll().forEach(inventory ->
                list.add(mapInventoryToDTO(inventory)));
        return list;
    }

    public Optional<InventoryDTO> getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .map(this::mapInventoryToDTO);
    }

    public boolean deleteInventoryById(Long id) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventoryRepository.delete(inventory);
            return true;
        }).orElse(false);
    }

    private InventoryDTO mapInventoryToDTO(Inventory inventory) {
        AssetDTO assetDTO = this.modelMapper.map(inventory.getAsset(), AssetDTO.class);
        return new InventoryDTO(inventory.getAssetId(), assetDTO, inventory.getCount());
    }


}
