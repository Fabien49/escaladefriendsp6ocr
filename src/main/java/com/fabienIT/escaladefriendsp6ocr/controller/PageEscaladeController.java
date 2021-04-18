package com.fabienIT.escaladefriendsp6ocr.controller;

import com.fabienIT.escaladefriendsp6ocr.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageEscaladeController {

    @Autowired
    TopoService topoService;


    @GetMapping("/sitePageEscaladeCo")
    public String user (Model model, Authentication authentication ) {

        String userName = authentication . getName ();
        String authorities =  authentication.getAuthorities().toString();
        model.addAttribute("userName", userName);

        return "pageEscaladeCo";
    }
}

