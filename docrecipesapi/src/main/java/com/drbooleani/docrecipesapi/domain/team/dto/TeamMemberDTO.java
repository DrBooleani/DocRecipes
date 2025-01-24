package com.drbooleani.docrecipesapi.domain.team.dto;

import com.drbooleani.docrecipesapi.domain.operation.dto.OperationProviderDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamMemberDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotNull(message = "Operation Provider cannot be null")
    @Valid
    private OperationProviderDTO operationProviderDTO;
}
