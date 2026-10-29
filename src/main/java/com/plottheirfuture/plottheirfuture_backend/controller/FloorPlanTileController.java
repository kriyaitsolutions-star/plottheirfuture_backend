package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.FloorPlanTile;
import com.plottheirfuture.plottheirfuture_backend.entity.TileStatus;
import com.plottheirfuture.plottheirfuture_backend.service.FloorPlanTileService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/floor-plan-tiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FloorPlanTileController {

    private final FloorPlanTileService floorPlanTileService;

    @GetMapping
    public ResponseEntity<List<FloorPlanTile>> getAllTiles() {
        return ResponseEntity.ok(floorPlanTileService.getAllTiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorPlanTile> getTileById(@PathVariable Long id) {
        return ResponseEntity.ok(floorPlanTileService.getTileById(id));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<FloorPlanTile>> getTilesByWedding(@PathVariable Long weddingId) {
        return ResponseEntity.ok(floorPlanTileService.getTilesByWeddingId(weddingId));
    }

    @GetMapping("/wedding/{weddingId}/zone/{zoneId}")
    public ResponseEntity<List<FloorPlanTile>> getTilesByZone(@PathVariable Long weddingId, @PathVariable Long zoneId) {
        return ResponseEntity.ok(floorPlanTileService.getTilesByWeddingAndZone(weddingId, zoneId));
    }

    @GetMapping("/wedding/{weddingId}/status")
    public ResponseEntity<List<FloorPlanTile>> getTilesByStatus(@PathVariable Long weddingId, @RequestParam TileStatus status) {
        return ResponseEntity.ok(floorPlanTileService.getTilesByWeddingAndStatus(weddingId, status));
    }

    @PostMapping
    public ResponseEntity<FloorPlanTile> createTile(@Valid @RequestBody FloorPlanTile tile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(floorPlanTileService.createTile(tile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FloorPlanTile> updateTile(@PathVariable Long id, @RequestBody FloorPlanTile tile) {
        return ResponseEntity.ok(floorPlanTileService.updateTile(id, tile));
    }

    @PostMapping("/{id}/lock")
    public ResponseEntity<FloorPlanTile> lockTile(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        String phone = (String) payload.get("phone");
        int minutes = payload.containsKey("minutes") ? ((Number) payload.get("minutes")).intValue() : 10;
        return ResponseEntity.ok(floorPlanTileService.lockTile(id, phone, minutes));
    }

    @PostMapping("/{id}/fund")
    public ResponseEntity<FloorPlanTile> markFunded(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Long donorId = payload.containsKey("donorId") ? ((Number) payload.get("donorId")).longValue() : null;
        return ResponseEntity.ok(floorPlanTileService.markFunded(id, donorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTile(@PathVariable Long id) {
        floorPlanTileService.deleteTile(id);
        return ResponseEntity.noContent().build();
    }
}
