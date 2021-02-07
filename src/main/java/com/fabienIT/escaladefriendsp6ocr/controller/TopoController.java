package com.fabienIT.escaladefriendsp6ocr.controller;


import com.fabienIT.escaladefriendsp6ocr.model.Reservation;
import com.fabienIT.escaladefriendsp6ocr.model.Site;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.repository.SiteRepository;
import com.fabienIT.escaladefriendsp6ocr.service.TopoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


@Controller
public class TopoController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    TopoService topoService;

    @Autowired
    UserController userController;

    @Autowired
    SiteRepository siteRepository;


    @GetMapping("/topoForm")
    public String topo(Model model) {
        model.addAttribute("topo", new Topo());
        return "topoForm";
    }

    @PostMapping("/enregistre")
    public String topoEnregistrer(Topo topo){
        topoService.ajouter(topo);
        log.info("Le topo que l'on ajoute est : " + topo);
        return "redirect:/topoListe";
    }

    @GetMapping("/monTopoForm")
    public String monTopo(Model model, Authentication authentication, Long id, HttpSession session) {
        List<Site> site = siteRepository.findAll();
        model.addAttribute("site", site);
        session.setAttribute("site", site);
        System.out.println("Les sites sont : " + site);
        model.addAttribute("topo", new Topo());
        return "monTopoForm";
    }

    @PostMapping("/monTopoEnregistre")
        public String monTopoEnregistrer(Model model, Authentication authentication, Topo topo, String nom, HttpSession session){
        User user = userController.userCo(model, authentication);
        int userId = user.getId();
        System.out.println("L'id de l'utilisateur qui enregistre le topo est : " + userId);
        topo.setUser(user);
        System.out.println("L'Id de l'utilisateur que de l'enregistrement est : " + user.getId());
        /*Site site = siteService.findByNom(nom);
        model.addAttribute(site);*/
        Site site = (Site) session.getAttribute("site");
        topo.setSite(site);
        topoService.ajouter(topo);
        log.info("Le topo que l'on ajoute est : " + topo);
        return "redirect:/mesTopos";
    }

    public void pasAfficherMesTopos(User user, Model model, Long id) {

        int userId = user.getId();
        Topo topo = topoService.findTopoById(id);
        long topoUserId = topo.getId();
        boolean pasAfficherMesTopos;
        if (userId == topoUserId) {
            pasAfficherMesTopos = true;
            model.addAttribute("pasAfficherMesTopos", pasAfficherMesTopos);
        }
        return;
    }

    @GetMapping("/topoListe")
    public String topoListAfficher (Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword, User user, Authentication authentication){
        //List<Topo> topoList = topoService.findAllTopo();
        user = userController.userCo(model, authentication);
        model.addAttribute("role", user);
        System.out.println("Le role esst : " + user);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        model.addAttribute("role", roles.toString());
        System.out.println("Le role est : " + roles.toString());

        Page<Topo> pageTopo = topoService.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("TopoListe", pageTopo.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", new int[pageTopo.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

        log.info("*********Le nombre de topo est : " + pageTopo.getTotalElements());

                return "topoListe";
    }


    @GetMapping("/mesTopos")
    public String mesTopos (Model model, Authentication authentication){

        Set<Topo> pageTopo = userController.userCo(model, authentication).getTopo();
        model.addAttribute("mesTopos", pageTopo);
        System.out.println("Les topos de l'utilisateur  sont  " + pageTopo);

        return "mesTopos";
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
        model.addAttribute("topoAfficher", t);
        log.info("Le topo que l'on souhaite réserver est : " + t);
        return "topoDemandeReservation";
    }
    @GetMapping("/validerReservation")
    public String topoValider (Model model, Long id){
        List<Topo> t = topoService.findAllTopo();
        model.addAttribute("valider", t );
        log.info("Le topo que l'on valide est : " + t);
        return "topoValidation";
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
        log.info("Le topo que l'on souhaite modifier est : " + t);
        return "topoModif";
    }

    @GetMapping("/monTopoListe")
    public String monTopoListAfficher (@Param("model") Model model,
                                       @Param("email") String email,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                    Long id, @Param("user") User user, @Param("authentification") Authentication authentication){
        //List<Topo> topoList = topoService.findAllTopo();
        user = userController.userCo(model, authentication);
        model.addAttribute("role", user);
        System.out.println("Le role est : " + user);
        email = user.getEmail();
        model.addAttribute("email", email);
        System.out.println("L'email de l'utilisateur connecté est : " + email);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        model.addAttribute("role", roles.toString());
        System.out.println("Le role est : " + roles.toString());

        Page<Topo> pageTopo = topoService.findAllNotId(PageRequest.of(page, size), model, user, authentication, userController);
        model.addAttribute("monTopoListe", pageTopo.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", new int[pageTopo.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

       /* int userId = user.getId();

        model.addAttribute(userId);
        System.out.println("l'Id de l'utilisateur pour le boolean est : " + userId);
        List<Topo> topo = topoService.findAllTopo();
        Topo topoUserId = topo.get(0);
        model.addAttribute(topoUserId);
        System.out.println("L'Id du topo pour le boolean est : " + topoUserId);

        boolean pasAfficherMesTopos;
        if (userId == topoUserId) {
            pasAfficherMesTopos = true;
            model.addAttribute("pasAfficherMesTopos", pasAfficherMesTopos);
        }*/

        log.info("*********Le nombre de topo est : " + pageTopo.getTotalElements());

        return "monTopoListe";
    }


    @PostMapping("/saveUpdateTopo")
    public String saveUpdateTopo (Model model, Topo topo){
        topoService.updateTopo(topo);
        model.addAttribute("update", topo);
        log.info("Le topo que l'on édite est : " + topo);
        return "redirect:/topoListe";
    }

    @GetMapping("/editerMonTopo")
    public String modifierMonTopo (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("topoModif", t );
        log.info("Le topo que l'on souhaite modifier est : " + t);
        return "monTopoModif";
    }

    @PostMapping("/saveUpdateMonTopo")
    public String saveUpdateMonTopo (Model model, Topo topo){
        topoService.updateTopo(topo);
        model.addAttribute("update", topo);
        log.info("Le topo que l'on édite est : " + topo);
        return "redirect:/mesTopos";
    }

    @GetMapping("/demandeReservationMonTopo")
    public String topoAfficherMonTopo (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("topoAfficherMonTopo", t);
        log.info("Le topo que l'on souhaite réserver est : " + t);
        return "topoDemandeReservationMonTopo";
    }

    @GetMapping("/validerReservationMonTopo")
    public String topoValiderMonTopo (Model model, Long id){
        Topo t = topoService.findTopoById(id);
        model.addAttribute("validerMonTopo", t );
        log.info("Le topo que l'on valide est : " + t);
        return "monTopoValidation";
    }





 /*   @PostMapping("/")
    public String demandeReservation (Model model, Long id){

    return "redirect:/topoListe";
    }*/




}




