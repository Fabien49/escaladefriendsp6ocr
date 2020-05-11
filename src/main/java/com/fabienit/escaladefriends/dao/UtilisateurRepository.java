package com.fabienit.escaladefriends.repository;

import com.fabienit.escaladefriends.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UtilisateurRepository extends JpaRepository <Utilisateur, Long>{


}