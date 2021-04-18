package com.fabienIT.escaladefriendsp6ocr.controller;

import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.repository.SiteRepository;
import com.fabienIT.escaladefriendsp6ocr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    UserService userService;

    @Autowired
    SiteRepository siteRepository;

    @RequestMapping(value="/admin/accueilAdmin", method = RequestMethod.GET)
    public ModelAndView accueilAdmin(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bonjour " + " : " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for users with Admin Role");
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        modelAndView.addObject("role", roles.toString());
        modelAndView.setViewName("accueilAdmin");
        return modelAndView;
    }
}
