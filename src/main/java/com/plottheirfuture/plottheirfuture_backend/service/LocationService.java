package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.Location;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
    }

    public List<Location> getLocationsByUserId(Long userId) {
        return locationRepository.findByUserIdOrderByPriorityAsc(userId);
    }

    @Transactional
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    public Location updateLocation(Long id, Location updated) {
        Location existing = getLocationById(id);

        if (updated.getUserId() != null) existing.setUserId(updated.getUserId());
        if (updated.getCity() != null) existing.setCity(updated.getCity());
        if (updated.getMandal() != null) existing.setMandal(updated.getMandal());
        if (updated.getDistrict() != null) existing.setDistrict(updated.getDistrict());
        if (updated.getConstituency() != null) existing.setConstituency(updated.getConstituency());
        if (updated.getPincode() != null) existing.setPincode(updated.getPincode());
        if (updated.getActive() != null) existing.setActive(updated.getActive());
        if (updated.getPriority() != null) existing.setPriority(updated.getPriority());

        return locationRepository.save(existing);
    }

    @Transactional
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
}
