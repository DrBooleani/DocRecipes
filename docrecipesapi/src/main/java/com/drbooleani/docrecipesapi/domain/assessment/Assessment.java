package com.drbooleani.docrecipesapi.domain.assessment;

import com.drbooleani.docrecipesapi.domain._embeddedids.AssessmentId;
import com.drbooleani.docrecipesapi.domain.patient.Patient;
import com.drbooleani.docrecipesapi.domain.team.TeamMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessments")
@NoArgsConstructor
@Getter
@Setter
public class Assessment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AssessmentId assessmentId;

    @ManyToOne
    @MapsId("teamMemberId")
    @JoinColumn(name = "team_member_id", columnDefinition = "BIGINT")
    private TeamMember teamMember;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id", columnDefinition = "BIGINT")
    private Patient patient;

    @ManyToOne
    @MapsId("preOpAId")
    @JoinColumn(name = "pre_op_a_id")
    private PreOperativeAssessment preOperativeAssessment;

    @Column(name = "start_date")
    private LocalDateTime startDate;

}
