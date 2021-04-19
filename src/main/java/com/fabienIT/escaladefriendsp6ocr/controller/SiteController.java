package com.fabienIT.escaladefriendsp6ocr.controller;

import com.fabienIT.escaladefriendsp6ocr.model.*;
import com.fabienIT.escaladefriendsp6ocr.repository.SiteRepository;
import com.fabienIT.escaladefriendsp6ocr.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
    CommentaireService commentaireService;

    @Autowired
    UserService userService;

    @Autowired
    UserController userController;


    @GetMapping("/siteCo")
    public String user (Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();
        model.addAttribute("userName", userName);
        log.info("L'utlisateur connecté est : " + userName);

        return "siteCo";
    }


    @GetMapping("/sites")
    public String sites (Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size,
                         @RequestParam(name = "keyword", defaultValue = "") String mc,
                         @RequestParam(name="region", defaultValue = "")String region,
                         Authentication authentication ) {
        Page<Site> sitePage = siteRepository.findByNomContainsAndRegionContains(mc, region, PageRequest.of(page, size));
        model.addAttribute("sitePage", sitePage.getContent());
        model.addAttribute("pages", new int[sitePage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        model.addAttribute("region", region);
        /*Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        model.addAttribute("role", roles.toString());
        System.out.println("Le role est : " + roles.toString());*/
        {

            try {
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                model.addAttribute("role", roles.toString());
                System.out.println("Le role est : " + roles.toString());
            } catch (NullPointerException e) {
                log.error("Pas de role");
            }
            log.info("Le nombre de site est : " + sitePage.getTotalElements());
           // log.info("Le role de l'utilisateur connecté est : " + role);

            if (sitePage.getTotalElements() == 0){
                model.addAttribute("recherchenull", "Aucun résultat ne correspond à vos critères de recherches");
                System.out.println("sitePage est vraiment null " + sitePage);
            }

            return "sites";
        }
    }

    @GetMapping("/siteListe")
    public String siteListe (Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size,
                         @RequestParam(name = "keyword", defaultValue = "") String mc,
                         @RequestParam(name="region", defaultValue = "")String region) {
        Page<Site> sitePage = siteRepository.findByNomContainsAndRegionContains(mc, region, PageRequest.of(page, size));
        model.addAttribute("siteListe", sitePage.getContent());
        model.addAttribute("pages", new int[sitePage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", mc);
        {
            log.info("Le nombre de site est : " + sitePage.getTotalElements());

            return "siteListe";
        }
    }

    @GetMapping("/siteForm")
    public String topo(Model model) {
        model.addAttribute("site", new Site());
        return "siteForm";
    }

    @PostMapping("/ajouterSite")
    public String siteEnregistrer(Site site){
        siteService.ajouter(site);
        log.info("Le site que l'on ajoute est : " + site);
        return "redirect:/sites";
    }

    @PostMapping("/saveUpdateSite")
    public String saveUpdateSite (Model model, Site site){

        siteService.updateSite(site);
        model.addAttribute("update", site);
        log.info("Le site que l'on édite est : " + site);

        return "redirect:/siteListe";
    }

    @GetMapping("/editerSiteCertifie")
    public String modifier (Model model, Long id){
        Site s = siteService.findSiteById(id);
        model.addAttribute("siteModif", s);
        log.info("Le site que l'on souhaite modifier est : " + s);
        return "siteModif";
    }

    @PostMapping("/saveUpdateSiteCertifie")
    public String saveUpdateSiteCertifie (Model model, Site site, Authentication authentication, User user) {

        try {
            user = userController.userCo(model, authentication);
            int userId = user.getId();
            model.addAttribute("userId", userId);
            System.out.println("L'ID de l'user connecté pour mettre un commentaire est : " + userId);
            System.out.println("L'ID du site est : " + site.getId());
            if (!site.isCertifie()) {
                model.addAttribute("certifie", site.isCertifie());
            }
        } catch (NullPointerException e) {
            log.error("Pas d'ID d'user connecté");
        }
            siteService.updateSiteCerfifie(site);
            model.addAttribute("update", site);
            log.info("Le site que l'on édite est : " + site);

        return "redirect:/sites";
    }


    @GetMapping("/effacerSite")
    public String effacer (Long id) {

        siteService.effacer(id);
        log.info("Le site que l'on vient d'effacer est : " + id);

        return "redirect:/siteListe";
    }


    @GetMapping("/admin/sitesMe")
    public String membre (Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();
        model.addAttribute("userName", userName);
        log.info("Le membre connecté est : " + userName);

        return "sitesMe";
    }

    @GetMapping("/pageEscalade")
    public String pageEscalade (Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size, Long id, Authentication authentication, HttpSession session, @ModelAttribute("siteId") Site site){
                if (id != null) {
                    site = siteService.findSiteById(id);
                    session.setAttribute("siteId", site);
                    System.out.println("L'Id du site est : " + site.getId());
                    model.addAttribute("siteId", site.getId());
                    model.addAttribute("sitePageEscalade", site);
                    if (site.isCertifie()){
                        model.addAttribute("certifie", site);
                    }
                    Set<Commentaire> comSite = siteService.findById(id).get().getCommentaire();
                    model.addAttribute("comSite", comSite);
                    model.addAttribute("commentaireForm", new Commentaire());

                    log.info("La page d'escalade est : " + site);
                    log.info("La liste des commentaires est : " +comSite);
                }else {
                    session.getId();
                    session.setAttribute("siteId", site);
                    System.out.println("L'Id du site est : " + site.getId());
                    Long siteId = site.getId();
                    model.addAttribute("siteId",siteId);
                    model.addAttribute("sitePageEscalade", site);
                    if (!site.isCertifie()){
                        model.addAttribute("certifie", site.isCertifie());
                    }
                    Set<Commentaire> comSite = siteService.findById(siteId).get().getCommentaire();
                    model.addAttribute("comSite", comSite);
                    model.addAttribute("commentaireForm", new Commentaire());

                    log.info("La page d'escalade est : " + site);
                    log.info("La liste des commentaires est : " +comSite);
                }

        try {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            model.addAttribute("role", roles.toArray()[0].toString());
            System.out.println("Le role est : " + roles.toString());
            String admin = new String("ADMIN");
            if ((roles.toString() != null) && (!site.isCertifie())){
                model.addAttribute("roles", roles.toString());
                System.out.println("la condition testée est bien prise en compte !!!!!!!!! youhou !!!!:!!!!!!!!!!");
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

            model.addAttribute("userId", user.getId());
            System.out.println("L'id de l'user est : " + user.getId());
        } catch (NullPointerException e) {
            log.error("Pas de role");
        }
        if (toString() != null) {
            log.info("La page d'escalade est : " + site);
        }
        return "sitePageEscalade";
    }


    @PostMapping("/saveCommentaireSite")
    public String saveCommentaireSite (Model model, Site site, Commentaire commentaire){
        siteService.updateSite(site);
        commentaireService.ajouterCom(commentaire);
        model.addAttribute(commentaire);
        model.addAttribute("update", site);
        log.info("Le site que l'on édite est : " + site);
        return "redirect:/pageEscalade";
    }



    @PostMapping("/pageEscalade")
    public String ajouterCom (User user, Long id, Model model, Commentaire commentaire, HttpSession session,
                              Authentication authentication, Site site, Long siteId, RedirectAttributes redirectAttributes){
        try {
            user = userController.userCo(model, authentication);
            int userId = user.getId();
            model.addAttribute("userId",userId);
            System.out.println("L'ID de l'user connecté pour mettre un commentaire est : " + userId);
            site = (Site) session.getAttribute("siteId");
            System.out.println("L'ID du site est : " + site.getId());
        } catch (NullPointerException e) {
            log.error("Pas d'ID d'user connecté");
        }
        try {
            commentaire.getCom();
            System.out.println("***********Le commentaire est :" + commentaire.getCom());
            commentaire.setComDate(LocalDateTime.now());
            System.out.println("***********La date du jour est : " + commentaire.getComDate());
            user = userController.userCo(model, authentication);
            commentaire.setUser(user);
            System.out.println("************L'id de l'user est : " + commentaire.getUser().getId());
//            siteId = (Long) session.getAttribute("siteId");
            session.getAttribute("siteId");
            commentaire.setSite(site);
            System.out.println("***********L'id du site est : " + commentaire.getSite().getId());
            commentaireService.ajouterCom(commentaire);
            model.addAttribute("commentaire", commentaire);
            log.info("Le commentaire que l'on ajoute est : " + commentaire);
        } catch (NullPointerException e) {
            log.error("Pas d'user connecté");
        }
        redirectAttributes.addFlashAttribute("siteId", site);
        return "redirect:/pageEscalade";
    }

    @GetMapping("/effacerCommentaire")
    public String effacerCommentaire (Model model, Commentaire commentaire, HttpSession session, User user, Long id,
                                      Authentication authentication, Site site, RedirectAttributes redirectAttributes) {
        try {
            user = userController.userCo(model, authentication);
            int userId = user.getId();
            model.addAttribute("userId",userId);
            System.out.println("L'ID de l'user connecté pour mettre un commentaire est : " + userId);
            site = (Site) session.getAttribute("siteId");
            System.out.println("L'ID du site est : " + site.getId());
            if (!site.isCertifie()){
                model.addAttribute("certifie", site.isCertifie());
            }
        } catch (NullPointerException e) {
            log.error("Pas d'ID d'user connecté");
        }
        try {
            commentaireService.effacer(id);
            log.info("Le commentaire que l'on vient d'effacer est : " + id);
        } catch (NullPointerException e) {
            log.error("Pas d'user connecté");
        }
        redirectAttributes.addFlashAttribute("siteId", site);

        return "redirect:/pageEscalade";
    }


   @GetMapping("/editerCommentaire")
    public String modifierCom (Model model, Long id){
        Commentaire commentaire = commentaireService.findById(id);
       System.out.println(id);
        model.addAttribute("comModif", commentaire);
        log.info("Le commentaire que l'on souhaite modifier est : " + commentaire);
        return "commentaireModif";
    }

    @PostMapping("/saveUpdateCommentaire")
    public String saveUpdateCommentaire (Model model, Commentaire commentaire, HttpSession session, User user, Long id,
                                         Authentication authentication, Site site, RedirectAttributes redirectAttributes){
        try {
            user = userController.userCo(model, authentication);
            int userId = user.getId();
            model.addAttribute("userId",userId);
            System.out.println("L'ID de l'user connecté pour mettre un commentaire est : " + userId);
            site = (Site) session.getAttribute("siteId");
            System.out.println("L'ID du site est : " + site.getId());
            if (!site.isCertifie()){
                model.addAttribute("certifie", site.isCertifie());
            }
        } catch (NullPointerException e) {
            log.error("Pas d'ID d'user connecté");
        }
        try {
            commentaireService.updateCommentaire(commentaire);
            id = commentaire.getSite().getId();
            model.addAttribute("commentaireModif", commentaire);
            log.info("Le commentaire que l'on édite est : " + commentaire);
        } catch (NullPointerException e) {
            log.error("Pas d'user connecté");
        }
        redirectAttributes.addFlashAttribute("siteId", site);

        return "redirect:/pageEscalade";
    }

}
