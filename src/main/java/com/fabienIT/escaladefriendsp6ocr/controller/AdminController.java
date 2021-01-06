package com.fabienIT.escaladefriendsp6ocr.controller;


import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    UserService userService;



    @GetMapping("/admin/accueilAdmin")
    public String accueilAdmin(Model model, String userName, Authentication authentication) {
    /*   User u = userService.findUser(id);
       model.addAttribute(u);*/
         userName = authentication . getName();
//         userName = userService.findByName(userName).getName();
        String authorities =  authentication.getAuthorities().toString();
        System.out.println("**************************** Name : "+userName);
        System.out.println("**************************** Authorities : "+authorities);

        model.addAttribute("userName", userName);

//       System.out.println("L'admin connecté est : " + u);

        return "accueilAdmin";
    }

}
