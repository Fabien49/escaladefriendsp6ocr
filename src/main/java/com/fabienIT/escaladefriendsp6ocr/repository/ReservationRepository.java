package com.fabienIT.escaladefriendsp6ocr.repository;

import com.fabienIT.escaladefriendsp6ocr.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(int id);

    List<Reservation> findByTopoId(Long id);

    Reservation findReservationById(Long id);

    Reservation findReservationByTopoId(long id);

    Reservation findReservationByUserId(int id);

}

