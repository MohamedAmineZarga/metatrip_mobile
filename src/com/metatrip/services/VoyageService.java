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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FLAM
 */
public class VoyageService {

    public ArrayList<voyage> voyages;
    
    public static VoyageService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
static int x;
    public VoyageService() {
         req = new ConnectionRequest();
         x=this.getAllTasks().size();
    }

    public static VoyageService getInstance() {
        if (instance == null) {
            instance = new VoyageService();
        }
        return instance;
    }
 

 
  
    public ArrayList<voyage> parseTasks(String jsonText){
        try {
            voyages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                voyage v = new voyage();
                float id = Float.parseFloat(obj.get("idv").toString());
                v.setIdv((int)id);
                
              
                  v.setImage_pays(obj.get("imagePays").toString());
                 v.setPays(obj.get("pays").toString());
voyages.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        System.out.println(voyages.toString());
        return voyages;
    }  
    
    
    
    
    
    public boolean addVoyage(voyage v) throws Exception {
        System.out.println(v);
        System.out.println("********");
        String url =  MyBd.BASE_URL+ "/voyage/new/json/"+ v.getPays()+"/"+v.getImage_pays();
       req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
       

       req.addArgument("pays", v.getPays());
           req.addArgument("Image_pays",v.getImage_pays());
       //req.addArgument("status", t.getStatus()+"");
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
    
    
        public boolean editVoyage(voyage v) {
 
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
    String url =  MyBd.BASE_URL + "/voyage/json/"+ v.getIdv()+"/"+ v.getPays()+"/"+v.getImage_pays();  
    req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
       req.addArgument("pays",v.getPays());
       req.addArgument("Image_pays", v.getImage_pays());
 
       //req.addArgument("status", t.getStatus()+"");
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
       

    
    public ArrayList<voyage> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url =  MyBd.BASE_URL+"/voyage/json";
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
        
        System.out.println(voyages.toString());
        System.out.println("nbbb"+voyages.size());
        return voyages;
    }
    
    
      public boolean deleteVoyage(int idv) {
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
             String url =  MyBd.BASE_URL+"/voyage/json/delll/"+idv;
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
