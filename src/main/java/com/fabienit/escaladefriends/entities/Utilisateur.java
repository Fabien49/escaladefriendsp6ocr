package com.fabienit.escaladefriends.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name="utilisateur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InscriptionForm {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String sexe;
    private String voie;
    private String codePostal;
    private String commune;
    private String niveau;

}