package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.Invitation;
import com.plottheirfuture.plottheirfuture_backend.entity.RsvpStatus;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public List<Invitation> getAllInvitations() {
        return invitationRepository.findAll();
    }

    public Invitation getInvitationById(Long id) {
        return invitationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invitation not found with id: " + id));
    }

    public List<Invitation> getInvitationsByWeddingId(Long weddingId) {
        return invitationRepository.findByWeddingId(weddingId);
    }

    public Invitation getInvitationByWeddingAndPhone(Long weddingId, String phoneE164) {
        return invitationRepository.findByWeddingIdAndPhoneE164(weddingId, phoneE164)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found on roster for this wedding."));
    }

    @Transactional
    public Invitation createInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    @Transactional
    public Invitation updateInvitation(Long id, Invitation updated) {
        Invitation existing = getInvitationById(id);

        if (updated.getGuestName() != null) existing.setGuestName(updated.getGuestName());
        if (updated.getPhoneE164() != null) existing.setPhoneE164(updated.getPhoneE164());
        if (updated.getCohort() != null) existing.setCohort(updated.getCohort());
        if (updated.getCeremonies() != null) existing.setCeremonies(updated.getCeremonies());
        if (updated.getRefLead() != null) existing.setRefLead(updated.getRefLead());
        if (updated.getRsvpStatus() != null) existing.setRsvpStatus(updated.getRsvpStatus());
        if (updated.getHeadcount() != null) existing.setHeadcount(updated.getHeadcount());

        return invitationRepository.save(existing);
    }

    @Transactional
    public Invitation updateRsvp(Long id, RsvpStatus status, Integer headcount) {
        Invitation existing = getInvitationById(id);
        existing.setRsvpStatus(status);
        if (headcount != null) {
            existing.setHeadcount(headcount);
        }
        return invitationRepository.save(existing);
    }

    @Transactional
    public void deleteInvitation(Long id) {
        if (!invitationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invitation not found with id: " + id);
        }
        invitationRepository.deleteById(id);
    }
}
