package com.fabienIT.escaladefriendsp6ocr.repository;

import com.fabienIT.escaladefriendsp6ocr.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface SiteRepository extends JpaRepository<Site, Long> {

    Page<Site> findByNomContainsAndRegionContains(String mc, String region, Pageable pageable);

    Optional<Site> findById(Long id);

    Site findAllById(Long id);

}
