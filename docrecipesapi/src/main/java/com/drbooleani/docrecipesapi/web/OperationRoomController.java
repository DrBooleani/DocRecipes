package com.drbooleani.docrecipesapi.web;

import com.drbooleani.docrecipesapi.domain.room.OperationRoomService;
import com.drbooleani.docrecipesapi.domain.room.dto.OperationRoomDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/operation-rooms")
public class OperationRoomController {

    private final OperationRoomService operationRoomService;

    public OperationRoomController(OperationRoomService operationRoomService) {
        this.operationRoomService = operationRoomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationRoomDTO> getOperationRoomById(@PathVariable Long id) {
        Optional<OperationRoomDTO> operationRoomDTOOptional = operationRoomService.getOperationRoomById(id);
        return operationRoomDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<OperationRoomDTO>> getOperationRooms() {
        List<OperationRoomDTO> operationRoomDTOList = operationRoomService.getAllOperationRooms();
        return ResponseEntity.status(HttpStatus.OK).body(operationRoomDTOList);
    }

    @PostMapping
    public ResponseEntity<OperationRoomDTO> createOperationRoom(@Valid @RequestBody OperationRoomDTO operationRoomDTO) {
        OperationRoomDTO operationRoomDTOCreated = operationRoomService.createOperationRoom(operationRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(operationRoomDTOCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationRoomDTO> updateOperationRoom(@PathVariable Long id,
                                                                @Valid @RequestBody OperationRoomDTO operationRoomDTO) {
        Optional<OperationRoomDTO> operationRoomDTOOptional = operationRoomService.updateOperationRoom(id, operationRoomDTO);
        return operationRoomDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationRoom(@PathVariable Long id) {
        boolean isDeleted = operationRoomService.deleteOperationRoomById(id);
        return isDeleted
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
