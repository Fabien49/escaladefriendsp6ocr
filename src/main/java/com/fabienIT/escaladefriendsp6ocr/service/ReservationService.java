package com.fabienIT.escaladefriendsp6ocr.service;

import com.fabienIT.escaladefriendsp6ocr.model.Reservation;
import com.fabienIT.escaladefriendsp6ocr.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	TopoService topoService;

	public void ajouter(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	public void effacerReservation(Long id) {
		reservationRepository.deleteById(id);
	}

	public List<Reservation> mesDemandes(int id){
		return reservationRepository.findByUserId(id);
	}

	public List<Reservation> mesTopos(int id){
		return reservationRepository.findByUserId(id);
	}

	public Reservation findReservationById(Long id) {
		return reservationRepository.findReservationById(id);
	}

	public Reservation findReservationByTopoId(long id){return reservationRepository.findReservationByTopoId(id);}

	public Reservation findReservationByUserId(int id){return reservationRepository.findReservationByUserId(id);}

	public Reservation updateReservation(Reservation reservation) {
		//recuparation du topo en base via l'id
		Long id = reservation.getId();
		Reservation dbReservation = reservationRepository.findById(id).get();
		//mise à jour (récupération) du nom depuis le formulaire d'edition
		dbReservation.setDemandeReservation(reservation.getDemandeReservation());
		dbReservation.setValiderReservation(reservation.getValiderReservation());
		dbReservation.setReserve(reservation.getReserve());
		//mise à jour dans la bdd (sauvegarde)
		return reservationRepository.save(dbReservation);

	}

	public void validerReservation (Long id, boolean accepted){
		Reservation dbReservation = reservationRepository.findById(id).get();
		dbReservation.setReserve(accepted);
		dbReservation.setDemandeReservation(false);
		dbReservation.setValiderReservation(true);
		reservationRepository.save(dbReservation);

		// Changer le statut su Topo
		topoService.updateTopoStatus(dbReservation.getTopo().getId(),!accepted);
	}


}
