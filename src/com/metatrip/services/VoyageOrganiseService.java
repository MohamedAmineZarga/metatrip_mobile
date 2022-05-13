/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.metatrip.utils.MyBd;
import com.mycompany.entities.voyage;
import com.mycompany.entities.voyage_organise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FLAM
 */
public class VoyageOrganiseService {
     public ArrayList<voyage_organise> voyages;
    
    public static VoyageOrganiseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
static int x;
    public VoyageOrganiseService() {
         req = new ConnectionRequest();
         x=this.getAllTasks().size();
    }

    public static VoyageOrganiseService getInstance() {
        if (instance == null) {
            instance = new VoyageOrganiseService();
        }
        return instance;
    }
 

 
  
    public ArrayList<voyage_organise> parseTasks(String jsonText){
        try {
            voyages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                voyage_organise v = new voyage_organise();
                float id = Float.parseFloat(obj.get("idvo").toString());
                v.setIdvo((int)id);
                
              
                  v.setAirline(obj.get("airline").toString());
                          
                              v.setEtatVoyage(obj.get("etatvoyage").toString());
           float prixBillet = Float.parseFloat(obj.get("prixBillet").toString());
           v.setPrix_billet(prixBillet);

                float nbplaces = Float.parseFloat(obj.get("nbplaces").toString());
                v.setNbplaces((int)nbplaces);
voyages.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        //System.out.println(voyages.toString());
        return voyages;
    }  
    
    
    
    
    
    public boolean addVoyage(voyage_organise v) throws Exception {
        System.out.println(v);
        System.out.println("********");
        String url =  MyBd.BASE_URL + "/voyage_organise/new/json/"+ v.getAirline()+"/"+v.getNbplaces()+"/"+v.getEtatVoyage()+"/"+v.getPrix_billet()+"/"+v.getIdv();
       req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
       

       req.addArgument("Prix_billet", String.valueOf(v.getPrix_billet()));
           req.addArgument("Airline",v.getAirline());
        req.addArgument("nbplaces", String.valueOf(v.getNbplaces()));
          req.addArgument("etatVoyage",v.getEtatVoyage());
        req.addArgument("Idv", String.valueOf(v.getIdv()));
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
 
        return resultOK;
    } 
    

        public boolean editVoyage(voyage_organise v) {
 
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
    String url = MyBd.BASE_URL + "/voyage_organise/update/json/"+ v.getIdvo()+"/"+v.getAirline()+"/"+v.getNbplaces()+"/"+v.getEtatVoyage()+"/"+v.getPrix_billet();  
    req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
     
 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       

    public ArrayList<voyage_organise> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url =  MyBd.BASE_URL+"/voyage_organise/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                voyages = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
       // System.out.println(voyages.toString());
         System.out.println("nb voy organse"+voyages.size());
        return voyages;
    }
    
    
   public boolean deleteVoyage(int idvo) {
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
             String url = MyBd.BASE_URL+"/voyage_organise/delete/json/"+idvo;
       req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
      
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
    
    
    
    
    
    
    
    
    
    
}
