package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.domain._embeddedids.OperationReportId;
import com.drbooleani.docrecipesapi.domain.team.TeamMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "operation_reports")
@NoArgsConstructor
@Getter
@Setter
public class OperationReport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OperationReportId operationReportId;

    @ManyToOne
    @MapsId("teamMemberId")
    @JoinColumn(name = "team_member_id", referencedColumnName = "id")
    private TeamMember teamMember;

    @ManyToOne
    @MapsId("operationId")
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;

    @Column(name = "report")
    private String report;
}
