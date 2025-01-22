package com.drbooleani.docrecipesapi.domain.assets;

import com.drbooleani.docrecipesapi.enums.AssetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "assets")
@NoArgsConstructor
@Getter
@Setter
public class Asset implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetType type;

    @Column(nullable = false)
    private String name;
}
