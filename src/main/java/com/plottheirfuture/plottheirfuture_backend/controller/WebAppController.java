package com.plottheirfuture.plottheirfuture_backend.controller;

import com.plottheirfuture.plottheirfuture_backend.entity.WebApp;
import com.plottheirfuture.plottheirfuture_backend.entity.WebAppType;
import com.plottheirfuture.plottheirfuture_backend.service.WebAppService;
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
@RequestMapping("/api/v1/web-apps")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WebAppController {

    private final WebAppService webAppService;

    @GetMapping
    public ResponseEntity<List<WebApp>> getAllWebApps() {
        return ResponseEntity.ok(webAppService.getAllWebApps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebApp> getWebAppById(@PathVariable Long id) {
        return ResponseEntity.ok(webAppService.getWebAppById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<WebApp>> getActiveWebApps() {
        return ResponseEntity.ok(webAppService.getActiveWebApps());
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<WebApp>> getWebAppsByType(@RequestParam WebAppType type) {
        return ResponseEntity.ok(webAppService.getWebAppsByType(type));
    }

    @PostMapping
    public ResponseEntity<WebApp> createWebApp(@Valid @RequestBody WebApp webApp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(webAppService.createWebApp(webApp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebApp> updateWebApp(@PathVariable Long id, @RequestBody WebApp webApp) {
        return ResponseEntity.ok(webAppService.updateWebApp(id, webApp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebApp(@PathVariable Long id) {
        webAppService.deleteWebApp(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/hero-images")
    public ResponseEntity<WebApp> addHeroImage(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String imageUrl = payload.get("imageUrl");
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl is required in body");
        }
        return ResponseEntity.ok(webAppService.addHeroImage(id, imageUrl));
    }

    @DeleteMapping("/{id}/hero-images")
    public ResponseEntity<WebApp> removeHeroImage(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String imageUrl = payload.get("imageUrl");
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl is required in body");
        }
        return ResponseEntity.ok(webAppService.removeHeroImage(id, imageUrl));
    }
}
