/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.entities;

import java.util.Date;

/**
 *
 * @author medal
 */
public class reservation_voyage {
    

private int Idrv	;
private Date Date_depart;	
private Date Date_arrivee;	
private String etat ;

private int Idu;	
private int Idv;	
private int Ref_paiement ;

    public reservation_voyage() {
    }

    public reservation_voyage(int Idrv, Date Date_depart, Date Date_arrivee, String etat, int Idu, int Idv, int Ref_paiement) {
        this.Idrv = Idrv;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
        this.etat = etat;
        this.Idu = Idu;
        this.Idv = Idv;
        this.Ref_paiement = Ref_paiement;
    }

    @Override
    public String toString() {
        return "reservation_voyage{" + "Idrv=" + Idrv + ", Date_depart=" + Date_depart + ", Date_arrivee=" + Date_arrivee + ", etat=" + etat + ", Idu=" + Idu + ", Idv=" + Idv + ", Ref_paiement=" + Ref_paiement + '}';
    }

    public int getIdrv() {
        return Idrv;
    }

    public Date getDate_depart() {
        return Date_depart;
    }

    public Date getDate_arrivee() {
        return Date_arrivee;
    }

    public String getEtat() {
        return etat;
    }

    public int getIdu() {
        return Idu;
    }

    public int getIdv() {
        return Idv;
    }

    public int getRef_paiement() {
        return Ref_paiement;
    }

    public void setIdrv(int Idrv) {
        this.Idrv = Idrv;
    }

    public void setDate_depart(Date Date_depart) {
        this.Date_depart = Date_depart;
    }

    public void setDate_arrivee(Date Date_arrivee) {
        this.Date_arrivee = Date_arrivee;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public void setIdv(int Idv) {
        this.Idv = Idv;
    }

    public void setRef_paiement(int Ref_paiement) {
        this.Ref_paiement = Ref_paiement;
    }

}
