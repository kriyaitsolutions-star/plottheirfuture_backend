package com.plottheirfuture.plottheirfuture_backend.repository;

import com.plottheirfuture.plottheirfuture_backend.entity.WebApp;
import com.plottheirfuture.plottheirfuture_backend.entity.WebAppType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebAppRepository extends JpaRepository<WebApp, Long> {

    List<WebApp> findByActiveTrue();

    Optional<WebApp> findByName(String name);

    List<WebApp> findByType(WebAppType type);
}
