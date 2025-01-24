package com.drbooleani.docrecipesapi.domain.inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    Optional<Inventory> findById(Long id);
}