package com.fabienIT.escaladefriendsp6ocr.controller;


import com.fabienIT.escaladefriendsp6ocr.EscaladeApplication;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.repository.TopoRepository;
import com.fabienIT.escaladefriendsp6ocr.service.TopoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TopoController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    TopoService topoService;

    @GetMapping("/topoForm")
    public String topo(Model model) {
        model.addAttribute("topo", new Topo());
        return "topoForm";
    }


    @PostMapping("/enregistre")
        public String topoEnregistrer(Topo topo){
        topoService.ajouter(topo);
        log.info("Le topo qque l'on ajoute est : " + topo);
        return "redirect:/topoListe";
    }

    @GetMapping("/topoListe")
    public String topoListAfficher (Model model, @Param("keyword")
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword){
        //List<Topo> topoList = topoService.findAllTopo();
        Page<Topo> pageTopo = topoService.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("TopoListe", pageTopo.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", new int[pageTopo.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

        log.info("*********Le nombre de topo est : " + pageTopo.getTotalElements());

                return "topoListe";
    }

    @GetMapping("/sitePageEscalade")
    public String topoListPageEscalade (Model model)
     {
        List<Topo> topoList = topoService.findAllTopo();
        model.addAttribute(topoList);
        log.info("La liste des topos est : " + topoList);

        return "sitePageEscalade";
    }

    @GetMapping("/demandeReservation")
    public String topoAfficher (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("topoAfficher", t );
        log.info("Le topo que l'on souhaite réserver est : " + t);
        return "topoDemandeReservation";
    }

    @GetMapping("/effacerTopo")
    public String effacer (Long id) {
        topoService.effacer(id);
        log.info("Le topo que l'on vient d'effacer est : " + id);
        return "redirect:/topoListe";
    }

    @GetMapping("/editerTopo")
        public String modifier (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("topoModif", t );
        log.info("Le topo que l'on souhaite modofier est : " + t);
        return "topoModif";
    }

    @PostMapping("/saveUpdateTopo")
    public String saveUpdateTopo (Model model, Topo topo){
        topoService.updateTopo(topo);
        model.addAttribute("update", topo);
        log.info("Le topo qque l'on édite est : " + topo);
        return "redirect:/topoListe";
    }

    @GetMapping("/validerReservation")
    public String topoValider (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("valider", t );
        log.info("Le topo que l'on valide est : " + t);
        return "topoValidation";
    }





 /*   @PostMapping("/")
    public String demandeReservation (Model model, Long id){

    return "redirect:/topoListe";
    }*/




}




