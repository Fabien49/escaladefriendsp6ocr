package com.fabienIT.escaladefriendsp6ocr.controller;

import com.fabienIT.escaladefriendsp6ocr.model.*;
import com.fabienIT.escaladefriendsp6ocr.service.ReservationService;
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
    ReservationService reservationService;


    @GetMapping("/topos")
    public String topo(Model model, Authentication authentication){

        try {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            model.addAttribute("role", roles.toString());
            System.out.println("Le role est : " + roles.toString());
        } catch (NullPointerException e) {
            log.error("Pas de role");
        }

        return "topos";
    }

    @GetMapping("/topoForm")
    public String topoForm(Model model, Authentication authentication) {
        try {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            model.addAttribute("role", roles.toString());
            System.out.println("Le role est : " + roles.toString());
        } catch (NullPointerException e) {
            log.error("Pas de role");
        }
        model.addAttribute("topo", new Topo());
        return "topoForm";
    }

    @PostMapping("/enregistre")
    public String topoEnregistrer(Model model, Topo topo, Authentication authentication){

        User user = userController.userCo(model, authentication);
        int userId = user.getId();
        topo.setUser(user);
        topoService.ajouter(topo);
        model.addAttribute("topo", topo);
        model.addAttribute("succesMessage", "Votre topo a bien été bien enregistré");

        return "redirect:/mesTopos";
    }

    @PostMapping("/monTopoEnregistre")
    public String monTopoEnregistrer(Model model, Authentication authentication, Topo topo, String nom, HttpSession session){

        User user = userController.userCo(model, authentication);
        int userId = user.getId();
        topo.setUser(user);
        topoService.ajouter(topo);
        model.addAttribute("topo", topo);
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
                                    @RequestParam(name = "size", defaultValue = "5") int size,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword,  Authentication authentication, Reservation reservation){

        User user = userController.userCo(model, authentication);
        model.addAttribute("roleUser", user.getId());
        System.out.println("L'ID est : " + user.getId());
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        model.addAttribute("role", roles.toString());
        Page<Topo> pageTopo = topoService.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("TopoListe", pageTopo.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", new int[pageTopo.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("role", roles.toString());
        model.addAttribute("reservation", reservation);
        log.info("*********Le nombre de topo est : " + pageTopo.getTotalElements());

        return "topoListe";
    }


    @GetMapping("/mesTopos")
    public String mesTopos (Model model, Authentication authentication, User user){

        Set<Topo> pageTopo = userController.userCo(model, authentication).getTopo();
        if ((pageTopo != null) && (user != null) ){
            model.addAttribute("mesTopos", pageTopo);

            try {
                Set<Reservation> reservation = userController.userCo(model, authentication).getReservation();
                if ((reservation != null) && (user != null) ){
                    model.addAttribute("reservation", reservation);
                    model.addAttribute("mesTopos", pageTopo);
                }
            } catch(NullPointerException e){
                log.error("Vous n'avez pas de de demande de réservation");
            }

            return "mesTopos";
        }
        return "redirect:/topos";
    }


    @GetMapping("/sitePageEscalade")
    public String topoListPageEscalade (Model model){

        List<Topo> topoList = topoService.findAllTopo();
        model.addAttribute(topoList);
        log.info("La liste des topos est : " + topoList);

        return "sitePageEscalade";
    }

    @GetMapping("/demandeReservation")
    public String topoAfficher (Model model, Long id, HttpSession session){

        Topo topo = topoService.findTopoById(id);
        model.addAttribute("topoAfficher", topo);
        session.setAttribute("topo", topo);
        model.addAttribute("reservation", new Reservation());
        log.info("Le topo que l'on souhaite réserver est : " + topo.getNom());
        return "topoDemandeReservation";
    }

    @GetMapping("/validerReservation")
    public String topoValider (Model model, Long id , HttpSession session){

        Topo topo = topoService.findTopoById(id);
        model.addAttribute("topoValider", topo);
        session.setAttribute("topoValider", topo);
        Reservation reservation = reservationService.findReservationByTopoId(id);
        if (reservation != null) {
            Topo topoReservation = reservationService.findReservationByTopoId(id).getTopo();
            model.addAttribute("reservation", reservation);
            model.addAttribute("topoReservation", topoReservation.getId());
        }
        model.addAttribute("erreur","Une erreur s'est produite");

        return "topoValidation";
    }

    @PostMapping("/saveValiderReservation")
    public String saveValiderReservation (Model model, Reservation reservation, HttpSession session, Authentication authentication){
        reservation = reservationService.updateReservation(reservation);
        Topo topo = reservation.getTopo();
        System.out.println(topo);
        model.addAttribute("updateReservation", reservation);
        model.addAttribute("topo", topo);
        User user = userController.userCo(model, authentication);
        model.addAttribute("userId", user.getId());
        log.info("Le topo que l'on édite est : " + reservation);

        return "/mesTopos";
    }

    @GetMapping ("/saveResa/{reservation_id}/{accepted}")
    public String saveResa (Model model, @PathVariable String reservation_id, @PathVariable boolean accepted){

        reservationService.validerReservation(Long.parseLong(reservation_id), accepted);

        return "redirect:/mesTopos";
    }


    @GetMapping("/effacerTopo")
    public String effacer (Long id) {

        topoService.delete(id);
        log.info("Le topo que l'on vient d'effacer est : " + id);

        return "redirect:/mesTopos";
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
                                       @RequestParam(name = "size", defaultValue = "5") int size,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                       Long id, @Param("user") User user, @Param("authentification") Authentication authentication, Reservation reservation){

        user = userController.userCo(model, authentication);
        model.addAttribute("role", user);
        System.out.println("Le role est : " + user);
        email = user.getEmail();
        model.addAttribute("email", email);
        System.out.println("L'email de l'user connecté est : " + email);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        model.addAttribute("role", roles.toString());
        System.out.println("Le role est : " + roles.toString());
        Page<Topo> pageTopo = topoService.findAllNotId(PageRequest.of(page, size), model, user, authentication, userController);
        model.addAttribute("monTopoListe", pageTopo.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", new int[pageTopo.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("reservation", reservation);
        System.out.println("*****Les réservations sont : " + reservation);
        System.out.println("L'Id de l'user qui fait la demande de réservation est : " + user.getId());
        log.info("*********Le nombre de topo est : " + pageTopo.getTotalElements());

        return "monTopoListe";
    }

    @GetMapping("/monTopoReservation")
    public String topoAfficherDemande (Model model, Long id, HttpSession session){

        Topo topo = topoService.findTopoById(id);
        model.addAttribute("topoAfficherDemande", topo);
        session.setAttribute("topo", topo);
        model.addAttribute("reservation", new Reservation());

        return "monTopoReservation";
    }

    @PostMapping("/saveUpdateMaDemande")
    public String saveUpdateMaDemande (Model model, Topo topo){

        topoService.updateTopo(topo);
        model.addAttribute("update", topo);
        log.info("Le topo que l'on édite est : " + topo);

        return "redirect:/mesDemandes";
    }

    @GetMapping("/mesDemandes")
    public  String  mesDemandes (Model model, HttpSession session, Authentication authentication) {

        User user = userController.userCo(model, authentication);
        int userId = user.getId();
        List<Reservation> reservation = reservationService.mesDemandes(userId);
        model.addAttribute("mesDemandes", reservation);

        return "mesDemandes";
    }


    @PostMapping("/saveUpdateTopo")
    public String saveUpdateTopo (Model model, Topo topo){

        topoService.updateTopo(topo);
        model.addAttribute("update", topo);
        log.info("Le topo que l'on édite est : " + topo);

        return "redirect:/mesTopos";
    }

    @GetMapping("/editerMonTopo")
    public String modifierMonTopo (Model model, Long id){

        Topo t = topoService.findTopoById(id);
        model.addAttribute("topoModif", t );
        log.info("Le topo que l'on souhaite modifier est : " + t);

        return "topoModif";
    }


    @PostMapping("/saveReservation")
    public String saveReservation (Model model, Reservation reservation, Long id, HttpSession session, Authentication authentication){

        Topo topo = (Topo) session.getAttribute("topo");
        topo.setDisponible(false);
        topoService.updateTopo(topo);
        id = topo.getId();
        reservation.setTopo(topo);
        User user = userController.userCo(model, authentication);
        int userId = user.getId();
        reservation.setUser(user);
        reservationService.ajouter(reservation);
        model.addAttribute("reservation", reservation);

        return "redirect:/topoListe";
    }


    @GetMapping("/validerReservationMonTopo")
    public String topoValiderMonTopo (Model model, Long id){

        Topo t = topoService.findTopoById(id);
        Set<Reservation> reservation = topoService.findTopoById(id).getReservation();
        model.addAttribute("validerMonTopo", t );
        model.addAttribute("reservation", reservation);
        log.info("Le topo que l'on valide est : " + t);

        return "monTopoValidation";
    }

    @GetMapping("/coordonnees")
    public String coordonnees (Model model, Long id){

        Topo topo = topoService.findTopoById(id);
        User user = topo.getUser();
        model.addAttribute("coordonneesProprietaire", user);
        Reservation reservation = reservationService.findReservationById(id);
        User userEmprunteur = reservation.getUseur();
        model.addAttribute("coordonneesEmprunteur", userEmprunteur);

        return "coordonnees";
    }

    @GetMapping("/validerAnnuler")
    public String effacerReservation (Long id, HttpSession session) {

        session.getAttribute("reservation");
        session.getId();
        reservationService.effacerReservation(id);
        log.info("La réservation que l'on vient d'annuler/effacer est : " + id);

        return "redirect:/mesDemandes";
    }

    @GetMapping("/annulerDemande")
    public String annulerDemande (Model model, Long id, HttpSession session){

        Reservation reservation = reservationService.findReservationById(id);
        Topo topo = reservation.getTopo();
        model.addAttribute("topoAfficherDemande", topo);
        model.addAttribute("annulerDemande", reservation );
        reservationService.effacerReservation(id);
        log.info("La réservation que l'on souhaite annuler est : " + reservation);

        return "redirect:/mesDemandes";
    }

}




