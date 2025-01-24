package com.drbooleani.docrecipesapi.domain.patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("patientRepository")
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
