
package com.mycompany.entities;

/**
 *
 * @author FLAM
 */
public class voyage_organise extends voyage{
    private int Idvo;
    private float Prix_billet;
    private String Airline;
    private int Nb_nuitees;
    private int nbplaces;
    private String etatVoyage;
    private voyage voyage;
    private int Idv;
    private String pays;
   
  

    public voyage_organise(int Idvo,float pb,String air,int nb,String pays, String Image_pays ,int nbplaces) {
        
        this.Idvo=Idvo;
        this.Prix_billet=pb;
        this.Airline=air;
        this.Nb_nuitees=nb;
        this.nbplaces=nbplaces;
      
       
    }
    
        public voyage_organise(float pb,String air,int nb) {
        
     
        this.Prix_billet=pb;
        this.Airline=air;
        this.Nb_nuitees=nb;
      
       
    }

    public voyage_organise(float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage,int nbplaces) {
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
          this.nbplaces=nbplaces;
        
    }


    public voyage_organise(float Prix_billet, String Airline, int Nb_nuitees, voyage voyage,int nbplaces) {
        
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.voyage = voyage;
          this.nbplaces=nbplaces;
    }
    

    
    
    
    public voyage_organise( int Idvo, float Prix_billet, String Airline, int Nb_nuitees, voyage voyage,int nbplaces) {
        //super(Idv, pays,Image_pays);
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.voyage = voyage;
          this.nbplaces=nbplaces;
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage, voyage voyage, String pays,int nbplaces) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
        this.pays = pays;
          this.nbplaces=nbplaces;
    }

 
  

    public String getPays() {
        return this.getVoyage().getPays();
    }

    public String getImage_pays() {
        return this.getVoyage().getImage_pays();
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage, voyage voyage,int nbplaces) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
          this.nbplaces=nbplaces;
    }

    public String getEtatVoyage() {
        return etatVoyage;
    }

    public void setEtatVoyage(String etatVoyage) {
        this.etatVoyage = etatVoyage;
    }

    public voyage_organise(float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage, voyage voyage,int nbplaces) {
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
          this.nbplaces=nbplaces;
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage,int Idv,int nbplaces) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.Idv=Idv;
          this.nbplaces=nbplaces;
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage, voyage voyage, int Idv, String pays,int nbplaces) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
        this.Idv = Idv;
        this.pays = pays;
          this.nbplaces=nbplaces;
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, String etatVoyage, int Idv, String pays,int nbplaces) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.Idv = Idv;
        this.pays = pays;
          this.nbplaces=nbplaces;
    }

  


    

        private String string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }

    

    public voyage_organise() {     this.Idvo=Idvo;
    }

    public int getIdvo() {
        return Idvo;
    }
    
     public int getIdv() {
        return Idv;
    }
       public void setIdv(int Idvo) {
        this.Idv = Idvo;
    }

    public float getPrix_billet() {
        return Prix_billet;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    
    
    public String getAirline() {
        return Airline;
    }

    public voyage getVoyage() {
        return voyage;
    }
public void setPays(String p){this.pays=p;}
    public void setVoyage(voyage voyage) {
        this.voyage = voyage;
    }

    public int getNb_nuitees() {
        return Nb_nuitees;
    }

    public void setIdvo(int Idvo) {
        this.Idvo = Idvo;
    }

    public void setPrix_billet(float Prix_billet) {
        this.Prix_billet = Prix_billet;
    }

    public void setAirline(String Airline) {
        this.Airline = Airline;
    }

    public void setNb_nuitees(int Nb_nuitees) {
        this.Nb_nuitees = Nb_nuitees;
    }

    @Override
    public String toString() {
        return "voyage_organise{" + "Idvo=" + Idvo + ", Prix_billet=" + Prix_billet + ", Airline=" + Airline + ", Nb_nuitees=" + Nb_nuitees + ", nbplaces=" + nbplaces + ", etatVoyage=" + etatVoyage + ", voyage=" + voyage + ", Idv=" + Idv + ", pays=" + pays + ", string=" + string + '}';
    }
    
    
    

 
  
    
    
    
}
