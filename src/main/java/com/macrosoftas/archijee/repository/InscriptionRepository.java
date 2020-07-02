package com.macrosoftas.archijee.repository;


import com.macrosoftas.archijee.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface InscriptionRepository extends JpaRepository<Utilisateur, Long> {

}
