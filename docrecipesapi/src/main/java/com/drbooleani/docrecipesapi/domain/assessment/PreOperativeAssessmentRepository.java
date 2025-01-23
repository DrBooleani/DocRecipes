package com.drbooleani.docrecipesapi.domain.assessment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreOperativeAssessmentRepository extends CrudRepository<PreOperativeAssessment, String> {
    Optional<PreOperativeAssessment> findByName(String name);
}
