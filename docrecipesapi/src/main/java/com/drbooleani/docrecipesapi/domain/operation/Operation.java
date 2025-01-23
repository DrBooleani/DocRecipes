package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.domain.patient.Patient;
import com.drbooleani.docrecipesapi.domain.room.OperationRoom;
import com.drbooleani.docrecipesapi.domain.team.TeamMember;
import com.drbooleani.docrecipesapi.enums.OperationState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operations")
@NoArgsConstructor
@Getter
@Setter
public class Operation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private OperationRoom operationRoom;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OperationState state;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "optype_team_member",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "team_member_id")
    )
    private Set<TeamMember> teamMembers = new HashSet<>();
}
