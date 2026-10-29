package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.Ceremony;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.CeremonyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CeremonyService {

    private final CeremonyRepository ceremonyRepository;

    public List<Ceremony> getAllCeremonies() {
        return ceremonyRepository.findAll();
    }

    public Ceremony getCeremonyById(Long id) {
        return ceremonyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ceremony not found with id: " + id));
    }

    public List<Ceremony> getCeremoniesByWeddingId(Long weddingId) {
        return ceremonyRepository.findByWeddingIdOrderByEventDateAscStartTimeAsc(weddingId);
    }

    @Transactional
    public Ceremony createCeremony(Ceremony ceremony) {
        return ceremonyRepository.save(ceremony);
    }

    @Transactional
    public Ceremony updateCeremony(Long id, Ceremony updated) {
        Ceremony existing = getCeremonyById(id);

        if (updated.getWeddingId() != null) existing.setWeddingId(updated.getWeddingId());
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getEventDate() != null) existing.setEventDate(updated.getEventDate());
        if (updated.getStartTime() != null) existing.setStartTime(updated.getStartTime());
        if (updated.getEndTime() != null) existing.setEndTime(updated.getEndTime());
        if (updated.getVenueName() != null) existing.setVenueName(updated.getVenueName());
        if (updated.getVenueMapUrl() != null) existing.setVenueMapUrl(updated.getVenueMapUrl());

        return ceremonyRepository.save(existing);
    }

    @Transactional
    public void deleteCeremony(Long id) {
        if (!ceremonyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ceremony not found with id: " + id);
        }
        ceremonyRepository.deleteById(id);
    }
}
