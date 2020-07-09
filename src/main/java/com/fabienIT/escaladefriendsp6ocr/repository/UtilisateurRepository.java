package com.macrosoftas.archijee.repository;


import com.macrosoftas.archijee.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    public Page<Utilisateur> findByNomContains(String mc, Pageable pageable);


}