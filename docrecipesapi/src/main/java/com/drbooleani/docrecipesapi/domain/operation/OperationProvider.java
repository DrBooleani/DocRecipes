package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.enums.OperationProviderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "operation_providers")
@NoArgsConstructor
@Getter
@Setter
public class OperationProvider implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationProviderType type;
}
