package com.drbooleani.docrecipesapi.domain.assessment;

import com.drbooleani.docrecipesapi.domain._embeddedids.AssessmentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment, AssessmentId> {
    Optional<Assessment> findById(AssessmentId assessmentId);
}