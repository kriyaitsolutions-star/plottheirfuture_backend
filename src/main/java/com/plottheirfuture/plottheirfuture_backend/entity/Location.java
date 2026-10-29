package com.plottheirfuture.plottheirfuture_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "city")
    private String city;

    @Column(name = "mandal")
    private String mandal;

    @Column(name = "district")
    private String district;

    @Column(name = "constituency")
    private String constituency;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = true;

    @Column(name = "priority")
    private Integer priority;
}
