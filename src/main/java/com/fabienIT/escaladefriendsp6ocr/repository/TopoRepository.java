package com.fabienIT.escaladefriendsp6ocr.repository;


import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

   Topo findAllById(Long id);
    //Topo  deleteById();

   Topo findByNom(String nom);

//@Query("SELECT t FROM Topo t WHERE CONCAT (t.nom, ' ',t.proprietaire, ' ',t.region, ' ',t.nbSites, ' ',t.nbVoies, ' ',t.cotationMin, ' ',t.cotationMax, ' ',t.demandeReservation, ' ',t.validerReservation,' ',t.reserve) LIKE %?1%")
   public Page<Topo> findByNomContains(String keyword, Pageable pageable);

    public Optional <Topo> findById (Long id);





}
