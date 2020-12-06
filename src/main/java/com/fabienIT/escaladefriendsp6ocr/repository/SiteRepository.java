package com.fabienIT.escaladefriendsp6ocr.repository;


import com.fabienIT.escaladefriendsp6ocr.model.Site;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SiteRepository extends JpaRepository<Site, Long> {

    public Page<Site> findByNomContains(String mc, Pageable pageable);

    public String findByNom(String nom);

     public String findById (Site site);

    Site findAllById(Long id);

}