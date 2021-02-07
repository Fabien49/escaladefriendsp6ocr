package com.fabienIT.escaladefriendsp6ocr.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id_demande;
    private Boolean demandeReservation = false;
    private Boolean validerReservation = false;
    private Boolean reserve;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topo_id")
    private Topo topo;

  /*  @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id_demande", nullable = true)
    private User user;*/

    public Reservation() {
    }

    public Reservation(Long id,Long user_id_demande, Boolean demandeReservation, Boolean validerReservation, Boolean reserve, Topo topo, User user) {
        this.id = id;
        this.user_id_demande = user_id_demande;
        this.demandeReservation = demandeReservation;
        this.validerReservation = validerReservation;
        this.reserve = reserve;
        this.topo = topo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id_demande() {
        return user_id_demande;
    }

    public void setUser_id_demande(Long user_id_demande) {
        this.user_id_demande = user_id_demande;
    }

    public Boolean getDemandeReservation() {
        return demandeReservation;
    }

    public void setDemandeReservation(Boolean demandeReservation) {
        this.demandeReservation = demandeReservation;
    }

    public Boolean getValiderReservation() {
        return validerReservation;
    }

    public void setValiderReservation(Boolean validerReservation) {
        this.validerReservation = validerReservation;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation topo = (Reservation) o;
        return id.equals(topo.id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", demandeReservation=" + demandeReservation +
                ", validerReservation=" + validerReservation +
                ", reserve=" + reserve +
                ", topo=" + topo +
                '}';
    }
}