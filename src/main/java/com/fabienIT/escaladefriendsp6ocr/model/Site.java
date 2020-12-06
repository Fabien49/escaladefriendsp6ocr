package com.fabienIT.escaladefriendsp6ocr.model;


import javax.persistence.*;

@Entity
@Table(name="site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String region;
    private int nbVoies;
    private String cotationMin;
    private String cotationMax;
    private String description;
    private String site_image;

    @ManyToOne
    @JoinColumn(name="topo_id")
    private Topo topo;

    public Site() {
    }

    public Site(Long id, String nom, String region, int nbVoies, String cotationMin, String cotationMax, String description, String site_image) {
        this.id = id;
        this.nom = nom;
        this.region = region;
        this.nbVoies = nbVoies;
        this.cotationMin = cotationMin;
        this.cotationMax = cotationMax;
        this.description = description;
        this.site_image = site_image;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite_image() {
        return site_image;
    }

    public void setSite_image(String site_image) {
        this.site_image = site_image;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", region='" + region + '\'' +
                ", nbVoies=" + nbVoies +
                ", cotationMin='" + cotationMin + '\'' +
                ", cotationMax='" + cotationMax + '\'' +
                ", description='" + description + '\'' +
                ", site_image='" + site_image + '\'' +
                '}';
    }
}