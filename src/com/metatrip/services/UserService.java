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
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.metatrip.entities.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.CENTER;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.metatrip.gui.HomeForm;
import com.metatrip.gui.Login;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.utils.MyBd;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medal
 */
public class UserService {

     Form current;
    
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
    public boolean AddUser(user u) throws Exception{
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
        //MailerService.sendMail(u);

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

        String url =  MyBd.BASE_URL+ "/user/supp/supp/supprimerfrommobile/"+u.getIdu();
         req.setUrl(url);
     
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
    }
        
        
    public void signin(TextField username,TextField password) {
        
        
        String url =  MyBd.BASE_URL+ "/Security/Security/mobile/loginuser/?email="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
         
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
     String json = new String(req.getResponseData()) + "";
            System.out.println(json);
            
            try {
             System.out.println(json);
            if(json.equals("login failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                 // new ListReclamationForm(rs).show();//yemchi lel list reclamation
           new AccueilBack().show() ;
                    
            }    
            }catch(Exception ex) {
                ex.printStackTrace();
            }
                  
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

        
    public void inscription(user u)  {
        
        
            String url = "http://127.0.0.1:8000/150/inscri/?prenom="+u.getPrenom().toString()+"&image="+u.getImage().toString()+"&datenaissance="+u.getDateNaissance()+"&tel="+u.getTel().toString()+"&email="+u.getEmail().toString()+"&password="+u.getPassword().toString()+"&cin="+u.getCin().toString()+"&nom="+u.getNom().toString();
       req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
      //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
         
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
     String json = new String(req.getResponseData()) + "";
            System.out.println(json);
            
            try {
             System.out.println(json);
            if(json.equals("inscription failed")) {
                Dialog.show("Echec d'inscription","OK",null);
            }
           if(json.equals("inscription done")) {
                 // new ListReclamationForm(rs).show();//yemchi lel list reclamation
          
                    
            }    
            }catch(Exception ex) {
             
            }
                  
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
    
