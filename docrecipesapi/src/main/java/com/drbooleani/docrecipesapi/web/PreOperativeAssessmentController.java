package com.drbooleani.docrecipesapi.web;

import com.drbooleani.docrecipesapi.domain.assessment.PreOperativeAssessmentService;
import com.drbooleani.docrecipesapi.domain.assessment.dto.PreOperativeAssessmentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pre-operative-assessments")
public class PreOperativeAssessmentController {

    private final PreOperativeAssessmentService preOperativeAssessmentService;

    public PreOperativeAssessmentController(PreOperativeAssessmentService preOperativeAssessmentService) {
        this.preOperativeAssessmentService = preOperativeAssessmentService;
    }

    @GetMapping
    public ResponseEntity<List<PreOperativeAssessmentDTO>> getPreOperativeAssessments() {
        List<PreOperativeAssessmentDTO> list = preOperativeAssessmentService.getAllPreOperativeAssessments();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> getPreOperativeAssessmentById(@PathVariable String name) {
        PreOperativeAssessmentDTO operativeAssessmentDTO = preOperativeAssessmentService.getPreOperativeAssessment(name);
        return ResponseEntity.ok(operativeAssessmentDTO);
    }

    @PostMapping
    public ResponseEntity<PreOperativeAssessmentDTO> createPreOperativeAssessment(@Valid @RequestBody PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        PreOperativeAssessmentDTO preOperativeAssessment = preOperativeAssessmentService.createPreOperativeAssessment(preOperativeAssessmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(preOperativeAssessment);
    }

    @PutMapping("/{name}")
    public ResponseEntity<PreOperativeAssessmentDTO> updatePreOperativeAssessment(@PathVariable String name,
                                                                                  @Valid @RequestBody PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        PreOperativeAssessmentDTO updatedAssessmentDTO = preOperativeAssessmentService.updatePreOperativeAssessment(name, preOperativeAssessmentDTO);
        return ResponseEntity.ok(updatedAssessmentDTO);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deletePreOperativeAssessment(@PathVariable String name) {
        preOperativeAssessmentService.deletePreOperativeAssessment(name);
        return ResponseEntity.noContent().build();
    }
}
