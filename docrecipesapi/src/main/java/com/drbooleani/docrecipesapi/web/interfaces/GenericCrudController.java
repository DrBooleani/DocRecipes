package com.drbooleani.docrecipesapi.web.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GenericCrudController<DTO> {

    @GetMapping
    ResponseEntity<List<DTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<DTO> getById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<DTO> create(@Valid @RequestBody DTO dto);

    @PutMapping("/{id}")
    ResponseEntity<DTO> update(@PathVariable Long id, @RequestBody DTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
