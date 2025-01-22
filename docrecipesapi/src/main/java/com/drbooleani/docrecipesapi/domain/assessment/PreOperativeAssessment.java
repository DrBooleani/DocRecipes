package com.drbooleani.docrecipesapi.domain.assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pre_operative_assessments")
@NoArgsConstructor
@Getter
@Setter
public class PreOperativeAssessment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String name;
}
