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
public class AssessmentId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "patient_id")
    private Long patientId;
}
