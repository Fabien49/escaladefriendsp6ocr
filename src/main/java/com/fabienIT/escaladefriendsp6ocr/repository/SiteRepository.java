package com.fabienIT.escaladefriendsp6ocr.repository;


import com.fabienIT.escaladefriendsp6ocr.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SiteRepository extends JpaRepository<Site, Long> {

    public Page<Site> findByNomContains(String mc, Pageable pageable);

    public String findByNom(String nom);

    Optional<Site> findById(Long id);

    Site findAllById(Long id);
}
