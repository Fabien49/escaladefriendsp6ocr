package com.fabienIT.escaladefriendsp6ocr.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Topo")
public class Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topo_id")
    private Long id;
    private String nom;
    private String proprietaire;
    private String region;
    private int nbSites;
    private int nbVoies;
    private String cotationMin;
    private String cotationMax;
    private Boolean demandeReservation = false;
    private Boolean validerReservation = false;
    private Boolean reserve;

    @OneToMany(mappedBy="topo")
    private Set<Site> site;


    public Topo() {
    }

    public Topo(Long id, String nom, String proprietaire, String region, int nbSites, int nbVoies, String cotationMin, String cotationMax, Boolean demandeReservation, Boolean validerReservation, Boolean reserve) {
        this.id = id;
        this.nom = nom;
        this.proprietaire = proprietaire;
        this.region = region;
        this.nbSites = nbSites;
        this.nbVoies = nbVoies;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.demandeReservation = demandeReservation;
        this.validerReservation = validerReservation;
        this.reserve = reserve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNbSites() {
        return nbSites;
    }

    public void setNbSites(int nbSites) {
        this.nbSites = nbSites;
    }

    public int getNbVoies() {
        return nbVoies;
    }

    public void setNbVoies(int nbVoies) {
        this.nbVoies = nbVoies;
    }

    public String getCotationMin() {
        return cotationMin;
    }

    public void setCotationMin(String cotationMin) {
        this.cotationMin = cotationMin;
    }

    public String getCotationMax() {
        return cotationMax;
    }

    public void setCotationMax(String cotationMax) {
        this.cotationMax = cotationMax;
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

    @Override
    public String toString() {
        return "Topo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", proprietaire='" + proprietaire + '\'' +
                ", region='" + region + '\'' +
                ", nbSites=" + nbSites +
                ", nbVoies=" + nbVoies +
                ", cotationMin='" + cotationMin + '\'' +
                ", cotationMax='" + cotationMax + '\'' +
                ", demandeReservation=" + demandeReservation +
                ", validerReservation=" + validerReservation +
                ", reserve=" + reserve +
                '}';
    }
}