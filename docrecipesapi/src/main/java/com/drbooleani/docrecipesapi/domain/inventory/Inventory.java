package com.drbooleani.docrecipesapi.domain.inventory;

import com.drbooleani.docrecipesapi.domain.assets.Asset;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@Getter
@Setter
public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "asset_id")
    private Long assetId;

    @OneToOne
    @MapsId
    @JoinColumn
    private Asset asset;

    private Integer count;
}
