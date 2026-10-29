package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.Invitation;
import com.plottheirfuture.plottheirfuture_backend.entity.RsvpStatus;
import com.plottheirfuture.plottheirfuture_backend.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/v1/invitations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Invitation>> getAllInvitations() {
        return ResponseEntity.ok(invitationService.getAllInvitations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitation> getInvitationById(@PathVariable Long id) {
        return ResponseEntity.ok(invitationService.getInvitationById(id));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<Invitation>> getInvitationsByWeddingId(@PathVariable Long weddingId) {
        return ResponseEntity.ok(invitationService.getInvitationsByWeddingId(weddingId));
    }

    @GetMapping("/wedding/{weddingId}/by-phone")
    public ResponseEntity<Invitation> getByWeddingAndPhone(@PathVariable Long weddingId, @RequestParam String phone) {
        return ResponseEntity.ok(invitationService.getInvitationByWeddingAndPhone(weddingId, phone));
    }

    @PostMapping
    public ResponseEntity<Invitation> createInvitation(@Valid @RequestBody Invitation invitation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invitationService.createInvitation(invitation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invitation> updateInvitation(@PathVariable Long id, @RequestBody Invitation invitation) {
        return ResponseEntity.ok(invitationService.updateInvitation(id, invitation));
    }

    @PatchMapping("/{id}/rsvp")
    public ResponseEntity<Invitation> updateRsvp(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        String statusStr = (String) payload.get("rsvpStatus");
        RsvpStatus status = RsvpStatus.valueOf(statusStr);
        Integer headcount = payload.containsKey("headcount") ? (Integer) payload.get("headcount") : null;
        return ResponseEntity.ok(invitationService.updateRsvp(id, status, headcount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long id) {
        invitationService.deleteInvitation(id);
        return ResponseEntity.noContent().build();
    }
}
