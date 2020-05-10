package com.fabienit.escaladefriends;

import com.fabienit.escaladefriends.Data.InscriptionDAO;
import com.fabienit.escaladefriends.InscriptionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscriptionController {


    @Autowired
    InscriptionDAO inscriptionDAO;

    @GetMapping("/")
    public String resultsForm (Model model ) {
        model.addAttribute("inscriptionForm", new InscriptionForm());
        return "index";
    }


    @PostMapping("/")
    public String resultsSubmit(@ModelAttribute InscriptionForm inscriptionForm) {

        return "results";
    }

}
