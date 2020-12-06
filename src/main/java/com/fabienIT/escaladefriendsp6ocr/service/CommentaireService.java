package com.fabienIT.escaladefriendsp6ocr.service;


import com.fabienIT.escaladefriendsp6ocr.model.Commentaire;
import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;

    public List<Commentaire> findAllCom (){return commentaireRepository.findAll(); }

    public void ajouterCom (Commentaire commentaire){
        commentaireRepository.save(commentaire);
    }

    public Commentaire findAllById(Long id) {
        return commentaireRepository.findAllById(id);
    }
}
