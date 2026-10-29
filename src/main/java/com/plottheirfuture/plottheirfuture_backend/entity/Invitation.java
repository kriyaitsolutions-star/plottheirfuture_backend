package com.plottheirfuture.plottheirfuture_backend.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invitations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wedding_id", nullable = false)
    private Long weddingId;

    @Column(name = "phone_e164", nullable = false)
    private String phoneE164;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "cohort")
    private String cohort;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "invitation_ceremonies", joinColumns = @JoinColumn(name = "invitation_id"))
    @Column(name = "ceremony_name")
    @Builder.Default
    private List<String> ceremonies = new ArrayList<>();

    @Column(name = "ref_lead")
    private String refLead;

    @Enumerated(EnumType.STRING)
    @Column(name = "rsvp_status")
    @Builder.Default
    private RsvpStatus rsvpStatus = RsvpStatus.PENDING;

    @Column(name = "headcount")
    @Builder.Default
    private Integer headcount = 1;
}
