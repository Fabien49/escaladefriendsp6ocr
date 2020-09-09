package com.fabienIT.escaladefriendsp6ocr.service;


import com.fabienIT.escaladefriendsp6ocr.model.Utilisateur;
import com.fabienIT.escaladefriendsp6ocr.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {


    @Autowired
    UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getUserList() {

        List<Utilisateur> maListe = new ArrayList<Utilisateur>();
        maListe = utilisateurRepository.findAll();

        return maListe;

    }

}