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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "web_app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Type is required and must be either 'app' or 'website'")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WebAppType type;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = true;

    @Column(name = "primary_colour")
    private String primaryColour;

    @Column(name = "secondary_colour")
    private String secondaryColour;

    @Column(name = "theme_colour")
    private String themeColour;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "web_app_hero_images", joinColumns = @JoinColumn(name = "web_app_id"))
    @Column(name = "image_url")
    @Builder.Default
    private List<String> heroImages = new ArrayList<>();
}
