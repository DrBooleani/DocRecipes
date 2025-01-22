package com.drbooleani.docrecipesapi.domain.room;

import com.drbooleani.docrecipesapi.enums.OperationRoomState;
import com.drbooleani.docrecipesapi.enums.OperationRoomType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "operation_rooms")
public class OperationRoom implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_nr")
    private Integer roomNr;

    @Column(name = "building_block")
    private String buildingBlock;

    @Column(name = "floor")
    private String floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationRoomType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OperationRoomState state;
 }
