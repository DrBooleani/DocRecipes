package com.drbooleani.docrecipesapi.domain.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRoomRepository extends CrudRepository<OperationRoom, Long> {
    Optional<OperationRoom> findById(Long id);
}
