package com.fabienIT.escaladefriendsp6ocr.model;


import javax.persistence.*;

@Entity
@Table(name="site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String nombreVoie;
    private String difficulte;
    private String description;
    private String technique;
    private String site_image;

    public Site() {
    }

    public Site(Long id, String nom, String nombreVoie, String difficulte, String description, String technique, String site_image) {
        this.id = id;
        this.nom = nom;
        this.nombreVoie = nombreVoie;
        this.difficulte = difficulte;
        this.description = description;
        this.technique = technique;
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

    public String getNombreVoie() {
        return nombreVoie;
    }

    public void setNombreVoie(String nombreVoie) {
        this.nombreVoie = nombreVoie;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
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
                ", nombreVoie='" + nombreVoie + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", description='" + description + '\'' +
                ", technique='" + technique + '\'' +
                ", site_image='" + site_image + '\'' +
                '}';
    }
}