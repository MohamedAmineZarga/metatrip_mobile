package com.metatrip.entities;

import java.util.Date;
import com.metatrip.utils.*;

public class Chauffeur {
    
    private int id;
     private String nom;
     private String prenom;
     private String image;
     private String tel;
     private String description;
     private String etatDispo;
    
    public Chauffeur() {}

    public Chauffeur(int id, String nom, String prenom, String image, String tel, String description, String etatDispo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }

    public Chauffeur(String nom, String prenom, String image, String tel, String description, String etatDispo) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getEtatDispo() {
        return etatDispo;
    }

    public void setEtatDispo(String etatDispo) {
        this.etatDispo = etatDispo;
    }
    
    
    
}