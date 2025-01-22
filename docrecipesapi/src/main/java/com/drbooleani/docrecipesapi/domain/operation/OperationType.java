package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.domain.assessment.PreOperativeAssessment;
import com.drbooleani.docrecipesapi.domain.assets.Asset;
import com.drbooleani.docrecipesapi.enums.OperationRoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operation_type")
@NoArgsConstructor
@Getter
@Setter
public class OperationType implements Serializable {

    @Id
    private String name;

    @Column(name = "room_type")
    private OperationRoomType roomType;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "optype_assets",
            joinColumns = @JoinColumn(name = "optype_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private Set<Asset> assets = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "optype_opproviders",
            joinColumns = @JoinColumn(name = "optype_id"),
            inverseJoinColumns = @JoinColumn(name = "opprovider_id")
    )
    private Set<OperationProvider> operationProviders= new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "optype_pre_op_a",
            joinColumns = @JoinColumn(name = "optype_id"),
            inverseJoinColumns = @JoinColumn(name = "pre_op_a_id")
    )
    private Set<PreOperativeAssessment> preOperativeAssessments = new HashSet<>();
}
