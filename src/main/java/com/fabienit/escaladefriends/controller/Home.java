package com.fabienit.escaladefriends;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "message", defaultValue = "Bienvenue") String message, Model model) {
        model.addAttribute("message", message);
        return "hello";
    }

  /* @GetMapping("/")
    public String accueil(@RequestParam(value = "name", defaultValue = "World") String name) {

        return "index";
    }*/

}
