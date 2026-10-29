package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanZone;
import com.plottheirfuture.plottheirfuture_backend.service.FloorPlanZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/floor-plan-zones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FloorPlanZoneController {

    private final FloorPlanZoneService floorPlanZoneService;

    @GetMapping
    public ResponseEntity<List<FloorPlanZone>> getAllZones() {
        return ResponseEntity.ok(floorPlanZoneService.getAllZones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorPlanZone> getZoneById(@PathVariable Long id) {
        return ResponseEntity.ok(floorPlanZoneService.getZoneById(id));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<FloorPlanZone>> getZonesByWeddingId(@PathVariable Long weddingId) {
        return ResponseEntity.ok(floorPlanZoneService.getZonesByWeddingId(weddingId));
    }

    @PostMapping
    public ResponseEntity<FloorPlanZone> createZone(@Valid @RequestBody FloorPlanZone zone) {
        return ResponseEntity.status(HttpStatus.CREATED).body(floorPlanZoneService.createZone(zone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FloorPlanZone> updateZone(@PathVariable Long id, @RequestBody FloorPlanZone zone) {
        return ResponseEntity.ok(floorPlanZoneService.updateZone(id, zone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        floorPlanZoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}
