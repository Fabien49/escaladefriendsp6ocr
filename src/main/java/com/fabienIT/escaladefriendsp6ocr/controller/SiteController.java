package com.fabienIT.escaladefriendsp6ocr.controller;


import com.fabienIT.escaladefriendsp6ocr.model.Commentaire;
import com.fabienIT.escaladefriendsp6ocr.model.Site;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.repository.SiteRepository;
import com.fabienIT.escaladefriendsp6ocr.service.CommentaireService;
import com.fabienIT.escaladefriendsp6ocr.service.SiteService;
import com.fabienIT.escaladefriendsp6ocr.service.TopoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
public class SiteController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    SiteService siteService;

    @Autowired
    TopoService topoService;

    @Autowired
    CommentaireService commentaireService;

    /*@GetMapping("/hello")
    public String hello(@RequestParam(value = "message", defaultValue = "Bienvenue") String message, Model model) {
        model.addAttribute("message", message);
        return "hello";
    }*/

    @GetMapping("/siteCo")
    public String utilisateur (Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();

        System.out.println("**************************** Name : "+userName);
        System.out.println("**************************** Authorities : "+authorities);

        model.addAttribute("userName", userName);

        log.info("L'utlisateur connecté est : " + userName);

        return "siteCo";
    }


    @GetMapping("/visiteur/sites")
    public String sites (Model model, Long topo,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size,
                         @RequestParam(name = "keyword", defaultValue = "") String mc, String keyword) {
        Page<Site> sitePage = siteRepository.findByNomContains(mc, PageRequest.of(page, size));

        model.addAttribute("sitePage", sitePage.getContent());
        model.addAttribute("pages", new int[sitePage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        {
            log.info("Le nombre de site est : " + sitePage.getTotalElements());

            return "sites";
        }

    }


    @GetMapping("/admin/sitesMe")
    public String membre (Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();

        System.out.println("**************************** Authorities : "+authorities);

        model.addAttribute("userName", userName);

        log.info("Le membre connecté est : " + userName);

        return "sitesMe";
    }

    @GetMapping("/visiteur/pageEscalade")
    public String pageEscalade (Model model,Long id,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Site s = siteService.findSiteById(id);
        model.addAttribute("sitePageEscalade", s );
        Set<Topo> toposite= siteService.findById(id).get().getTopo();
        model.addAttribute("toposite", toposite);
        log.info("La page d'escalade est : " + s);
        log.info("Le topo est : " + toposite);
        return "sitePageEscalade";
    }

}

