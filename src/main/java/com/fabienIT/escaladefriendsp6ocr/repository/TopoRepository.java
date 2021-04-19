package com.fabienIT.escaladefriendsp6ocr.repository;

import com.fabienIT.escaladefriendsp6ocr.model.Topo;
import com.fabienIT.escaladefriendsp6ocr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

    Page<Topo> findByNomContains(@Param("keyword") String keyword, @Param("page") Pageable pageable);

    @Query("SELECT t FROM Topo t WHERE t.user NOT LIKE :user ")
    Page<Topo> findAllNotId(@Param("page") Pageable pageable, @Param("user") User user);

    Optional <Topo> findById (Long id);

    Topo findTopoById (Long id);

}
