package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.Ceremony;
import com.plottheirfuture.plottheirfuture_backend.service.CeremonyService;
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
@RequestMapping("/api/v1/ceremonies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CeremonyController {

    private final CeremonyService ceremonyService;

    @GetMapping
    public ResponseEntity<List<Ceremony>> getAllCeremonies() {
        return ResponseEntity.ok(ceremonyService.getAllCeremonies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ceremony> getCeremonyById(@PathVariable Long id) {
        return ResponseEntity.ok(ceremonyService.getCeremonyById(id));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<Ceremony>> getCeremoniesByWeddingId(@PathVariable Long weddingId) {
        return ResponseEntity.ok(ceremonyService.getCeremoniesByWeddingId(weddingId));
    }

    @PostMapping
    public ResponseEntity<Ceremony> createCeremony(@Valid @RequestBody Ceremony ceremony) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ceremonyService.createCeremony(ceremony));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ceremony> updateCeremony(@PathVariable Long id, @RequestBody Ceremony ceremony) {
        return ResponseEntity.ok(ceremonyService.updateCeremony(id, ceremony));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCeremony(@PathVariable Long id) {
        ceremonyService.deleteCeremony(id);
        return ResponseEntity.noContent().build();
    }
}
