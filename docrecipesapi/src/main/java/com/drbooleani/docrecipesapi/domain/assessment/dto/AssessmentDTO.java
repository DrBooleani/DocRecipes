package com.drbooleani.docrecipesapi.domain.assessment.dto;

import com.drbooleani.docrecipesapi.domain.patient.dto.PatientDTO;
import com.drbooleani.docrecipesapi.domain.team.dto.TeamMemberDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssessmentDTO {
    @NotNull(message = "Team Member Id cannot be null")
    private Long teamMemberId;

    @NotNull(message = "PreOpAssessment Id cannot be null")
    private String preOpAssessmentId;

    @NotNull(message = "PatientId cannot be null")
    private Long patientId;

    private LocalDateTime startDate;
    private TeamMemberDTO teamMember;
    private PreOperativeAssessmentDTO preOperativeAssessment;
    private PatientDTO patient;

}