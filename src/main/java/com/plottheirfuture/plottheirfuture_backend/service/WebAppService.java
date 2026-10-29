package com.plottheirfuture.plottheirfuture_backend.service;

import com.plottheirfuture.plottheirfuture_backend.entity.WebApp;
import com.plottheirfuture.plottheirfuture_backend.entity.WebAppType;
import com.plottheirfuture.plottheirfuture_backend.exception.ResourceNotFoundException;
import com.plottheirfuture.plottheirfuture_backend.repository.WebAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebAppService {

    private final WebAppRepository webAppRepository;

    public List<WebApp> getAllWebApps() {
        return webAppRepository.findAll();
    }

    public WebApp getWebAppById(Long id) {
        return webAppRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WebApp not found with id: " + id));
    }

    public List<WebApp> getActiveWebApps() {
        return webAppRepository.findByActiveTrue();
    }

    public List<WebApp> getWebAppsByType(WebAppType type) {
        return webAppRepository.findByType(type);
    }

    @Transactional
    public WebApp createWebApp(WebApp webApp) {
        return webAppRepository.save(webApp);
    }

    @Transactional
    public WebApp updateWebApp(Long id, WebApp updated) {
        WebApp existing = getWebAppById(id);

        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getType() != null) existing.setType(updated.getType());
        if (updated.getActive() != null) existing.setActive(updated.getActive());
        if (updated.getPrimaryColour() != null) existing.setPrimaryColour(updated.getPrimaryColour());
        if (updated.getSecondaryColour() != null) existing.setSecondaryColour(updated.getSecondaryColour());
        if (updated.getThemeColour() != null) existing.setThemeColour(updated.getThemeColour());
        if (updated.getHeroImages() != null) existing.setHeroImages(updated.getHeroImages());

        return webAppRepository.save(existing);
    }

    @Transactional
    public void deleteWebApp(Long id) {
        if (!webAppRepository.existsById(id)) {
            throw new ResourceNotFoundException("WebApp not found with id: " + id);
        }
        webAppRepository.deleteById(id);
    }

    @Transactional
    public WebApp addHeroImage(Long id, String imageUrl) {
        WebApp webApp = getWebAppById(id);
        webApp.getHeroImages().add(imageUrl);
        return webAppRepository.save(webApp);
    }

    @Transactional
    public WebApp removeHeroImage(Long id, String imageUrl) {
        WebApp webApp = getWebAppById(id);
        webApp.getHeroImages().remove(imageUrl);
        return webAppRepository.save(webApp);
    }
}
