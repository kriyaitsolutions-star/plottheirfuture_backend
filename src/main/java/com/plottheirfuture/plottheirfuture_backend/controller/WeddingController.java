package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.Wedding;
import com.plottheirfuture.plottheirfuture_backend.entity.WeddingStatus;
import com.plottheirfuture.plottheirfuture_backend.service.WeddingService;
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

@RestController
@RequestMapping("/api/v1/weddings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeddingController {

    private final WeddingService weddingService;

    @GetMapping
    public ResponseEntity<List<Wedding>> getAllWeddings() {
        return ResponseEntity.ok(weddingService.getAllWeddings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wedding> getWeddingById(@PathVariable Long id) {
        return ResponseEntity.ok(weddingService.getWeddingById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Wedding> getWeddingBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(weddingService.getWeddingBySlug(slug));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Wedding>> getWeddingsByStatus(@RequestParam WeddingStatus status) {
        return ResponseEntity.ok(weddingService.getWeddingsByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Wedding> createWedding(@Valid @RequestBody Wedding wedding) {
        return ResponseEntity.status(HttpStatus.CREATED).body(weddingService.createWedding(wedding));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wedding> updateWedding(@PathVariable Long id, @RequestBody Wedding wedding) {
        return ResponseEntity.ok(weddingService.updateWedding(id, wedding));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWedding(@PathVariable Long id) {
        weddingService.deleteWedding(id);
        return ResponseEntity.noContent().build();
    }
}
