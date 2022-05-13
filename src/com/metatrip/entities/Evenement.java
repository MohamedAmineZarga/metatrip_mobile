/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.entities;

import java.util.Date;




/**
 *
 * @author Z4RGA
 */
public class Evenement {

    private int Ide;
    private String Type_event	;
        private String Chanteur	;
    private String Adresse;
    private Date Date_event ; 
    private float  prix_e ; 
    private String image;

    public Evenement() {
    }

    public Evenement(String Type_event, String Chanteur, String Adresse, Date Date_event, float prix_e, String image) {
        this.Type_event = Type_event;
        this.Chanteur = Chanteur;
        this.Adresse = Adresse;
        this.Date_event = Date_event;
        this.prix_e = prix_e;
        this.image = image;
    }

    public Evenement(String Type_event, String Chanteur, String Adresse, float prix_e, String image) {
        this.Type_event = Type_event;
        this.Chanteur = Chanteur;
        this.Adresse = Adresse;
        this.prix_e = prix_e;
        this.image = image;
    }

    public int getIde() {
        return Ide;
    }

    @Override
    public String toString() {
        return "Evenement{" + "Ide=" + Ide + ", Type_event=" + Type_event + ", Chanteur=" + Chanteur + ", Adresse=" + Adresse + ", Date_event=" + Date_event + ", prix_e=" + prix_e + ", image=" + image + '}';
    }

    public String getType_event() {
        return Type_event;
    }

    public void setIde(int Ide) {
        this.Ide = Ide;
    }

    public void setType_event(String Type_event) {
        this.Type_event = Type_event;
    }

    public void setChanteur(String Chanteur) {
        this.Chanteur = Chanteur;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setDate_event(Date Date_event) {
        this.Date_event = Date_event;
    }

    public void setPrix_e(float prix_e) {
        this.prix_e = prix_e;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChanteur() {
        return Chanteur;
    }

    public String getAdresse() {
        return Adresse;
    }

    public Date getDate_event() {
        return Date_event;
    }

    public float getPrix_e() {
        return prix_e;
    }

    public String getImage() {
        return image;
    }
    

    
   
}
