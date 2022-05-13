package com.metatrip.entities;

import java.util.Date;
import com.metatrip.utils.*;

public class Abonnement {
    
    private int id;
     private String type;
     private int prix;
     private Date dateAchat;
     private Date dateExpiration;
     private String etat;
    
    public Abonnement() {}

    public Abonnement(int id, String type, int prix, Date dateAchat, Date dateExpiration, String etat) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.dateAchat = dateAchat;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public Abonnement(String type, int prix, Date dateAchat, Date dateExpiration, String etat) {
        this.type = type;
        this.prix = prix;
        this.dateAchat = dateAchat;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    
    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
}