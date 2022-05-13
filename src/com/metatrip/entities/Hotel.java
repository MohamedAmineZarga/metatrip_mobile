package com.metatrip.entities;

import java.util.Date;
import com.metatrip.utils.*;

public class Hotel implements Comparable<Hotel> {
    
    private int id;
     private String nom;
     private int nbEtoiles;
     private String image;
     private String adresse;
    
    public Hotel() {}

    public Hotel(int id, String nom, int nbEtoiles, String image, String adresse) {
        this.id = id;
        this.nom = nom;
        this.nbEtoiles = nbEtoiles;
        this.image = image;
        this.adresse = adresse;
    }

    public Hotel(String nom, int nbEtoiles, String image, String adresse) {
        this.nom = nom;
        this.nbEtoiles = nbEtoiles;
        this.image = image;
        this.adresse = adresse;
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
    
    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
    @Override
    public int compareTo(Hotel hotel) {
        switch (Statics.compareVar) {
            case "Nom":
                 return this.getNom().compareTo(hotel.getNom());
            case "NbEtoiles":
                return Integer.compare(this.getNbEtoiles(), hotel.getNbEtoiles());
            case "Adresse":
                 return this.getAdresse().compareTo(hotel.getAdresse());
            
            default:
                return 0;
        }
    }
    
}