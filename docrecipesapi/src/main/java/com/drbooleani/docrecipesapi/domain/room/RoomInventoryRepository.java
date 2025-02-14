package com.drbooleani.docrecipesapi.domain.room;

import com.drbooleani.docrecipesapi.domain._embeddedids.RoomInventoryId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomInventoryRepository extends CrudRepository<RoomInventory, RoomInventoryId> {
    Optional<RoomInventory> findById(RoomInventoryId roomInventoryId);
}