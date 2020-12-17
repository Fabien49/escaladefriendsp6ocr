package com.fabienIT.escaladefriendsp6ocr.service;


import com.fabienIT.escaladefriendsp6ocr.model.Site;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteService {

    @Autowired
    SiteRepository siteRepository;

    public void ajouter(Site site) {
        siteRepository.save(site);
    }

    public Site findSiteById(Long id) {
        return (Site) siteRepository.findAllById(id);
    }

    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }

}




