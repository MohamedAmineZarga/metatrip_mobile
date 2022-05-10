/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author medal
 */
public class user {
    
    private int Idu ;
    private String Nom;
    private String Prenom ;
    private String Tel;
    private String Email; 
    private String Password;
    private String Image;
    private int Role;
    private String dateNaissance;
    private String cin;

    public user() {
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public user(String Nom, String Prenom, String Tel, String Email, String Password, String Image, String dateNaissance,String cin) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.dateNaissance = dateNaissance;
        this.cin=cin;
    }

    public user(int Idu, String Nom, String Prenom, String Tel, String Email, String Password, String Image, int Role, String dateNaissance, String cin) {
        this.Idu = Idu;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.Role = Role;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
    }

    public user(String Nom, String Prenom, String Tel, String Email, String Password, String Image, int Role, String dateNaissance, String cin) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.Role = Role;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "user{" + "Idu=" + Idu + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", Password=" + Password + ", Image=" + Image + ", Role=" + Role + ", dateNaissance=" + dateNaissance + ", cin=" + cin + '}';
    }


 

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getIdu() {
        return Idu;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getTel() {
        return Tel;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getImage() {
        return Image;
    }

    public int getRole() {
        return Role;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }
    
    
}
