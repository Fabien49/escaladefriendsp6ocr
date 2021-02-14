package com.fabienIT.escaladefriendsp6ocr.service;

import com.fabienIT.escaladefriendsp6ocr.controller.UserController;
import com.fabienIT.escaladefriendsp6ocr.model.Reservation;
import com.fabienIT.escaladefriendsp6ocr.model.User;
import com.fabienIT.escaladefriendsp6ocr.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	public void ajouter(Reservation reservation) {
		reservationRepository.save(reservation);
	}

/*	public List<Reservation> findAllReservation (){return reservationRepository.findAll(); }

	public Reservation findReservationById(Long id) {
		return reservationRepository.findReservationById(id);
	}

	public Reservation findReservationByNom(String reservation) {
		return reservationRepository.findByNom(reservation);
	}

	public Optional<Reservation> findTopoSite(Long reservation){return reservationRepository.findById(reservation);}*/



/*public List <Topo> findAllTopo (){
		return topoRepository.findAll();
	}*//*


*/
/*	public Page<Reservation> findAllNotId (Pageable pageable, @Param("model") Model model, @Param("user") User user, @Param("authentification") Authentication authentication, UserController userController){

		user = userController.userCo(model, authentication);

		return reservationRepository.findAllNotId(pageable, user);
	}


	public Page<Reservation> findByNameContains (String keyword, Pageable pageable) {
		if (keyword != null) {
			return reservationRepository.findByNomContains(keyword, pageable);
		}
		System.out.println("Aucunes correspondances à votre recherche !!!");
		return reservationRepository.findAll(pageable);
	}*//*


	public void effacer(Long id) {
		reservationRepository.deleteById(id);
	}

*/
/*

	public void updateTopo(Reservation reservation) {
		//recuparation du topo en base via l'id
		Long id = topo.getId();
		Topo dbTopo = reservationRepository.findById(id).get();
		//mise à jour (récupération) du nom depuis le formulaire d'edition
		dbTopo.setNom(topo.getNom());
		dbTopo.setProprietaire(topo.getProprietaire());
		dbTopo.setRegion(topo.getRegion());
		dbTopo.setNbSites(topo.getNbSites());
		dbTopo.setNbVoies(topo.getNbVoies());
		dbTopo.setCotationMin(topo.getCotationMin());
		dbTopo.setCotationMax(topo.getCotationMax());
		//mise à jour dans la bdd (sauvegarde)
		reservationRepository.save(dbTopo);

	}*/

}
