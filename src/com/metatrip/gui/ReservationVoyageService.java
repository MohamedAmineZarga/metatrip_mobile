/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.metatrip.entities.user;
import com.metatrip.entities.reservation_voyage;
import com.metatrip.utils.MyBd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medal
 */
public class ReservationVoyageService {
     Form current;
    
public static  ReservationVoyageService instance =null;

    


    public ArrayList<reservation_voyage> rvs;
private ConnectionRequest req;
 public boolean resultOK;
 
public static ReservationVoyageService getInstance(){
    if(instance==null)
        instance =new ReservationVoyageService();
    return instance;
    
    
}


    public ReservationVoyageService() {
        req = new ConnectionRequest();
    }
    
     
    public ArrayList<reservation_voyage> parseRev(String jsonText) throws ParseException{
          rvs = new ArrayList<>();
        try {
          
            JSONParser j = new JSONParser();
            Map<String,Object> RevlistJson = 
              j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)RevlistJson.get("root");
            for(Map<String,Object> obj : list){
                reservation_voyage t = new reservation_voyage();
                float id = Float.parseFloat(obj.get("idrv").toString());
                t.setIdrv((int)id);
      String DateDP= obj.get("dateDepart").toString();

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String str2 = formater.format(DateDP);
                Date dateDP1 = formater.parse(str2);
       t.setDate_depart(dateDP1);
       
       String strIdU = (obj.get("idu").toString());                       
                t.setIdu((int)Float.parseFloat(strIdU.substring(strIdU.indexOf(":")+1,strIdU.indexOf(",")))); 
              
           
             rvs.add(t);  
            }   
        } catch (IOException ex) {
            System.out.println(ex);
            
        }
  
        return rvs;
    }

  public ArrayList<reservation_voyage> getAllrvs(){
       
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
      
        String url =  MyBd.BASE_URL+"/reservation_voyage/json/jsonreservation";
        req.setUrl(url);
        req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       //  rvs = parseRev(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
  
        return rvs;
    }
}
