package com.macrosoftas.archijee.controller;


import com.macrosoftas.archijee.model.Utilisateur;
import com.macrosoftas.archijee.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscriptionController {


    @Autowired
    InscriptionService inscriptionService;
    // mettre USERsERVICEiMPL

    @GetMapping("/callFormInscription")
    public String resultsForm (Model model) {
        model.addAttribute("inscriptionForm", new Utilisateur());
        return "inscription";
    }


    @PostMapping("/results")
    public String utilisateurSubmit(@ModelAttribute Utilisateur utilisateur) {
        System.out.println("*****************Le nom d'utilisateur est : " +utilisateur);
        inscriptionService.ajouter(utilisateur);
        return "results";
    }

}
