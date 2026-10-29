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

@Entity
@Table(name = "weddings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "bride_name", nullable = false)
    private String brideName;

    @Column(name = "groom_name", nullable = false)
    private String groomName;

    @Column(name = "target_sft")
    @Builder.Default
    private Integer targetSft = 1200;

    @Column(name = "rate_sft")
    @Builder.Default
    private Double rateSft = 4500.0;

    @Column(name = "video_stream_url")
    private String videoStreamUrl;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private WeddingStatus status = WeddingStatus.ACTIVE;

    @Column(name = "builder_rera_id")
    private String builderReraId;

    @Column(name = "escrow_account_number")
    private String escrowAccountNumber;
}
