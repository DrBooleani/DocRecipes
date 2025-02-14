package com.drbooleani.docrecipesapi.domain.team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
    Optional<TeamMember> findById(Long id);
}