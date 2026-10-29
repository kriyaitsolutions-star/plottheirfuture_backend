package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.Wedding;
import com.plottheirfuture.plottheirfuture_backend.entity.WeddingStatus;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.WeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeddingService {

    private final WeddingRepository weddingRepository;

    public List<Wedding> getAllWeddings() {
        return weddingRepository.findAll();
    }

    public Wedding getWeddingById(Long id) {
        return weddingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wedding not found with id: " + id));
    }

    public Wedding getWeddingBySlug(String slug) {
        return weddingRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Wedding not found with slug: " + slug));
    }

    public List<Wedding> getWeddingsByStatus(WeddingStatus status) {
        return weddingRepository.findByStatus(status);
    }

    @Transactional
    public Wedding createWedding(Wedding wedding) {
        if (wedding.getSlug() != null && weddingRepository.existsBySlug(wedding.getSlug())) {
            throw new IllegalArgumentException("Wedding slug already exists: " + wedding.getSlug());
        }
        return weddingRepository.save(wedding);
    }

    @Transactional
    public Wedding updateWedding(Long id, Wedding updated) {
        Wedding existing = getWeddingById(id);

        if (updated.getSlug() != null && !updated.getSlug().equals(existing.getSlug())) {
            if (weddingRepository.existsBySlug(updated.getSlug())) {
                throw new IllegalArgumentException("Wedding slug already exists: " + updated.getSlug());
            }
            existing.setSlug(updated.getSlug());
        }
        if (updated.getBrideName() != null) existing.setBrideName(updated.getBrideName());
        if (updated.getGroomName() != null) existing.setGroomName(updated.getGroomName());
        if (updated.getTargetSft() != null) existing.setTargetSft(updated.getTargetSft());
        if (updated.getRateSft() != null) existing.setRateSft(updated.getRateSft());
        if (updated.getVideoStreamUrl() != null) existing.setVideoStreamUrl(updated.getVideoStreamUrl());
        if (updated.getCoverImageUrl() != null) existing.setCoverImageUrl(updated.getCoverImageUrl());
        if (updated.getStatus() != null) existing.setStatus(updated.getStatus());
        if (updated.getBuilderReraId() != null) existing.setBuilderReraId(updated.getBuilderReraId());
        if (updated.getEscrowAccountNumber() != null) existing.setEscrowAccountNumber(updated.getEscrowAccountNumber());

        return weddingRepository.save(existing);
    }

    @Transactional
    public void deleteWedding(Long id) {
        if (!weddingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Wedding not found with id: " + id);
        }
        weddingRepository.deleteById(id);
    }
}
