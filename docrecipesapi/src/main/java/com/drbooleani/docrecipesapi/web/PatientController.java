package com.drbooleani.docrecipesapi.web;

import com.drbooleani.docrecipesapi.domain.patient.PatientService;
import com.drbooleani.docrecipesapi.domain.patient.dto.PatientDTO;
import com.drbooleani.docrecipesapi.web.interfaces.GenericCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController implements GenericCrudController<PatientDTO> {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.ok(this.patientService.getAllPatients());
    }

    @Override
    public ResponseEntity<PatientDTO> getById(Long id) {
        return ResponseEntity.ok(this.patientService.getPatientById(id));
    }

    @Override
    public ResponseEntity<PatientDTO> create(PatientDTO patientDTO) {
        PatientDTO response = this.patientService.savePatient(patientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<PatientDTO> update(Long id, PatientDTO patientDTO) {
        return ResponseEntity.ok(this.patientService.updatePatient(id, patientDTO));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        this.patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
