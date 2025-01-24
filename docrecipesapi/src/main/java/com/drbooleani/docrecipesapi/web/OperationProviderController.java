package com.drbooleani.docrecipesapi.web;

import com.drbooleani.docrecipesapi.domain.operation.OperationProviderService;
import com.drbooleani.docrecipesapi.domain.operation.dto.OperationProviderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/operation-providers")
public class OperationProviderController {

    private final OperationProviderService operationProviderService;

    public OperationProviderController(OperationProviderService operationProviderService) {
        this.operationProviderService = operationProviderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> getOperationProvider(@PathVariable String id) {
        OperationProviderDTO operationProviderDTO = operationProviderService.getOperationProviderById(id);
        return ResponseEntity.ok(operationProviderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OperationProviderDTO>> getAllOperationProviders() {
        List<OperationProviderDTO> operationProviders = operationProviderService.getAllOperationProviders();
        return ResponseEntity.ok(operationProviders);
    }

    @PostMapping
    public ResponseEntity<OperationProviderDTO> createOperationProvider(@Valid @RequestBody OperationProviderDTO operationProviderDTO) {
        OperationProviderDTO createdOperationProvider = operationProviderService.createOperationProvider(operationProviderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOperationProvider.getType()).toUri();
        return ResponseEntity.created(uri).body(createdOperationProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> updateOperationProvider(@PathVariable String id,
                                                                        @Valid @RequestBody OperationProviderDTO operationProviderDTO) {
        OperationProviderDTO operationProviderDTOUpdated = operationProviderService.updateOperationProvider(id, operationProviderDTO);
        return ResponseEntity.ok(operationProviderDTOUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationProvider(@PathVariable String id) {
        return operationProviderService.deleteOperationProvider(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}