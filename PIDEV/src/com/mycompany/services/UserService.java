/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.user;
import com.mycompany.utilis.MyBd;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author medal
 */
public class UserService {
    
public static  UserService instance =null;

    public ArrayList<user> users;
private ConnectionRequest req;
 public boolean resultOK;
 
public static UserService getInstance(){
    if(instance==null)
        instance =new UserService();
    return instance;
    
    
}

    public UserService() {
        req = new ConnectionRequest();
    }
    public boolean AddUser(user u){
        ArrayList<user> result= new ArrayList<>();
          
 
         String url = MyBd.BASE_URL + "/user/t/adduser/";
         req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
        
        req.addArgument("cin", u.getCin().toString());
       req.addArgument("nom", u.getNom().toString());
       req.addArgument("email", u.getEmail().toString());
          req.addArgument("prenom", u.getPrenom().toString());
         req.addArgument("image", u.getImage().toString());
          req.addArgument("tel", u.getTel().toString());
             req.addArgument("password", u.getPassword().toString());
              
              req.addArgument("datenaissance", u.getDateNaissance());
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
   
    
    
    
    public ArrayList<user> parseUser(String jsonText){
          users = new ArrayList<>();
        try {
          
            JSONParser j = new JSONParser();
            Map<String,Object> userListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
            for(Map<String,Object> obj : list){
                user t = new user();
                float id = Float.parseFloat(obj.get("idu").toString());
                t.setIdu((int)id);
       String email =obj.get("email").toString();
       t.setEmail(email);
                t.setPassword(obj.get("password").toString());  
                t.setPrenom(obj.get("prenom").toString());
                t.setTel(obj.get("tel").toString());
                       t.setCin(obj.get("cin").toString());
                                t.setImage(obj.get("image").toString());
             users.add(t);  
            }   
        } catch (IOException ex) {
            
        }
  
        return users;
    }

  public ArrayList<user> getAllusers(){
       
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
      
        String url =  MyBd.BASE_URL+"/user/get/users";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
  
        return users;
    }
     public boolean editUser(user u) {
        System.out.println(u);
        System.out.println("********");
       String url =  MyBd.BASE_URL+ "/user/10/modifieruser/"+u.getIdu();
       req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
     req.addArgument("cin", u.getCin().toString());
       req.addArgument("nom", u.getNom().toString());
       req.addArgument("email", u.getEmail().toString());
          req.addArgument("prenom", u.getPrenom().toString());
         req.addArgument("image", u.getImage().toString());
          req.addArgument("tel", u.getTel().toString());
             req.addArgument("password", u.getPassword().toString());
       
       System.out.println(req);
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
     
        public void supprimeruser(user u) {

        String url =  MyBd.BASE_URL+ "/user/10/supp/suppmob/"+u.getIdu();
         req.setUrl(url);
     
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }
}
