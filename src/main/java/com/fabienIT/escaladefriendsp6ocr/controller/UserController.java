package com.fabienIT.escaladefriendsp6ocr.controller;



import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.model.Utilisateur;
import com.fabienIT.escaladefriendsp6ocr.repository.UtilisateurRepository;
import com.fabienIT.escaladefriendsp6ocr.service.UserService;
import com.fabienIT.escaladefriendsp6ocr.service.UtilisateurService;
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

import java.util.List;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UserService userService;

/*    @GetMapping("/inscription")
    public String utilisateur(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "/inscription";
    }*/

    @PostMapping("/utilisateurAjouter")
    public String ajouter (Utilisateur utilisateur) {
        utilisateurService.ajouter(utilisateur);
        log.info("Le nom de l'utilisateur est : " +utilisateur);
        return "/home";
    }


    @GetMapping("/utilisateurCo")
    public String admin(Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();

        System.out.println("**************************** Name : "+userName);
        System.out.println("**************************** Authorities : "+authorities);

        model.addAttribute("userName", userName);

        return "utilisateurCo";
    }

    /*@GetMapping(value = "/utilisateur")
    public String list(Model model,
            @RequestParam(name = "page", defaultValue = "0")int page,
            @RequestParam(name="size",defaultValue = "5")int size) {
        Page<Utilisateur> utilisateurPage = utilisateurService.getUserList(PageRequest.of(page,size));
        model.addAttribute("utilisateurPage",utilisateurPage.getContent());
        model.addAttribute("pages", new int[utilisateurPage.getTotalPages()]);
         return "utilisateur";

    }*/

/*  @GetMapping("/admin/userListe")
    public String userListAfficher (Model model, Long id,
                        @RequestParam(name = "page", defaultValue = "0")int page,
                        @RequestParam(name="size",defaultValue = "5")int size,
                        @RequestParam(name="keyword",defaultValue = "")String mc) {
        //Page<User> userPage = userService.findByNomContains(mc, PageRequest.of(page, size));
       // List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        log.info("La liste des utilisateurs est : " + userList);
        return "userListe";
    }*/

/*    @GetMapping("/deleteUser")
    public String delete (Long id, String keyword, int page, int size) {
        userService.deleteById(id);
        log.info("L'id de l'utilisateur supprimé est : " + id);
        return "redirect:/utilisateur?page="+page+"&size="+size+"&keyword="+keyword;
    }*/

    @GetMapping("/editUser")
    public String edit (Model model, Long id) {
        Utilisateur u =  utilisateurService.findUtilisateurById(id);
        model.addAttribute("utilisateur", u);
        log.info("L'utilisateur modifié est : " + u);
        return "modifier";
    }


}
