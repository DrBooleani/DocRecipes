package com.drbooleani.docrecipesapi.domain._embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OperationReportId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "operation_id")
    private Long operationId;
}
