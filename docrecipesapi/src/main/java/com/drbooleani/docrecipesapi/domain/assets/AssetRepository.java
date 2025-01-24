package com.drbooleani.docrecipesapi.domain.assets;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("assetRepository")
public interface AssetRepository extends CrudRepository<Asset, Long> {
}
