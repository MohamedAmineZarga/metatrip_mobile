 
package com.mycompany.entities;

public class voyage {
    
    private int Idv;
    private String  pays;
    private String Image_pays;

    public voyage(String pays, String Image_pays) {
        this.pays = pays;
        this.Image_pays = Image_pays;
    }

    public voyage(int Idv,String pays, String Image_pays) {
        this.Idv = Idv;
        this.pays = pays;
        this.Image_pays = Image_pays;
    }

    public voyage(int Idv, String pays) {
        this.Idv = Idv;
        this.pays = pays;
    }

    
    
        public voyage(){}

    public voyage(int Idv) {
        this.Idv = Idv;
    }
    
        
    public int getIdv() {
        return Idv;
    }

    public String getPays() {
        return pays;
    }

    public String getImage_pays() {
        return Image_pays;
    }

    public void setIdv(int Idv) {
        this.Idv = Idv;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setImage_pays(String Image_pays) {
        this.Image_pays = Image_pays;
    }

    @Override
    public String toString() {
        return "voyage{" + "Idv=" + Idv + ", pays=" + pays + ", Image_pays=" + Image_pays + '}';
    }
    
    
    
    

    
    
    }

