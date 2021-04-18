package com.fabienIT.escaladefriendsp6ocr.controller;

import com.fabienIT.escaladefriendsp6ocr.model.Commentaire;
import com.fabienIT.escaladefriendsp6ocr.service.CommentaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommentaireController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    CommentaireService commentaireService;

    @GetMapping("/commentaireAfficher")
    public  String commentaireAfficher (Model model){
        List<Commentaire> commentaireList = commentaireService.findAllCom();
        model.addAttribute("commentaireList", commentaireList);
        return "redirect:/commentaire";
    }
}
