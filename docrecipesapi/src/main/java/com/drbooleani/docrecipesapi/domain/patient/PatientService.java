package com.drbooleani.docrecipesapi.domain.patient;

import com.drbooleani.docrecipesapi.config.GenericCrudService;
import com.drbooleani.docrecipesapi.domain.patient.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final GenericCrudService<Patient, PatientDTO> genericCrudService;

    private final String patientRepository = "patientRepository";

    @Autowired
    public PatientService(GenericCrudService<Patient, PatientDTO> genericCrudService) {
        this.genericCrudService = genericCrudService;
    }

    public List<PatientDTO> getAllPatients() {
        return this.genericCrudService.getAll(PatientDTO.class, patientRepository);
    }

    public PatientDTO getPatientById(Long id) {
        return this.genericCrudService.getById(id, Patient.class, PatientDTO.class, patientRepository);
    }

    public PatientDTO savePatient(PatientDTO patientDTO) {
        return this.genericCrudService.save(patientDTO, Patient.class, PatientDTO.class, patientRepository);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        return this.genericCrudService.update(id, patientDTO, Patient.class, PatientDTO.class, patientRepository);
    }

    public void deletePatient(Long id) {
        this.genericCrudService.delete(id, Patient.class, patientRepository);
    }
}
