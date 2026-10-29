package com.plottheirfuture.plottheirfuture_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "floor_plan_tiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FloorPlanTile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wedding_id", nullable = false)
    private Long weddingId;

    @Column(name = "zone_id", nullable = false)
    private Long zoneId;

    @Column(name = "sft_size", nullable = false)
    @Builder.Default
    private Integer sftSize = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private TileStatus status = TileStatus.OPEN;

    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;

    @Column(name = "locked_by_phone")
    private String lockedByPhone;

    @Column(name = "donor_id")
    private Long donorId;
}
