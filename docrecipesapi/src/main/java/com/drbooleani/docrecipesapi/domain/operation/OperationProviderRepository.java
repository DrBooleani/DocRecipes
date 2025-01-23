package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.enums.OperationProviderType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationProviderRepository extends CrudRepository<OperationProvider, OperationProviderType> {
    Optional<OperationProvider> findByType(OperationProviderType type);
}