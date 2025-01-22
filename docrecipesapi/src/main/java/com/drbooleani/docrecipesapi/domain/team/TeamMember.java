package com.drbooleani.docrecipesapi.domain.team;

import com.drbooleani.docrecipesapi.domain.operation.OperationProvider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "team_member")
@NoArgsConstructor
@Getter
@Setter
public class TeamMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "opprovider_id")
    private OperationProvider operationProvider;
}
