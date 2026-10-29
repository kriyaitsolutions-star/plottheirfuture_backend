package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.Contribution;
import com.plottheirfuture.plottheirfuture_backend.service.ContributionService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1/contributions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContributionController {

    private final ContributionService contributionService;

    @GetMapping
    public ResponseEntity<List<Contribution>> getAllContributions() {
        return ResponseEntity.ok(contributionService.getAllContributions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contribution> getContributionById(@PathVariable Long id) {
        return ResponseEntity.ok(contributionService.getContributionById(id));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<Contribution>> getContributionsByWedding(@PathVariable Long weddingId) {
        return ResponseEntity.ok(contributionService.getContributionsByWeddingId(weddingId));
    }

    @GetMapping("/wedding/{weddingId}/successful")
    public ResponseEntity<List<Contribution>> getSuccessfulContributions(@PathVariable Long weddingId) {
        return ResponseEntity.ok(contributionService.getSuccessfulContributions(weddingId));
    }

    @PostMapping
    public ResponseEntity<Contribution> createContribution(@Valid @RequestBody Contribution contribution) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contributionService.createContribution(contribution));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contribution> updateContribution(@PathVariable Long id, @RequestBody Contribution contribution) {
        return ResponseEntity.ok(contributionService.updateContribution(id, contribution));
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Contribution> confirmPayment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String txnId = payload.get("txnId");
        String bankRef = payload.get("bankRef");
        return ResponseEntity.ok(contributionService.confirmPayment(id, txnId, bankRef));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContribution(@PathVariable Long id) {
        contributionService.deleteContribution(id);
        return ResponseEntity.noContent().build();
    }
}
