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
import com.metatrip.entities.Evenement;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class serviceEvenement {

    public ArrayList<Evenement> Evenement;

    public static serviceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public serviceEvenement() {
        req = new ConnectionRequest();
    }

    public static serviceEvenement getInstance() {
        if (instance == null) {
            instance = new serviceEvenement();
        }
        return instance;
    }

//    public boolean addCat(Evenement t) {
//            String url = Statics.BASE_URL + "/addjs/new?type="+ t.getType();
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
    public ArrayList<Evenement> parseCat(String jsonText) {
        try {
            Evenement = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> EvenementListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) EvenementListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Evenement e = new Evenement();

                try {
                    float id = Float.parseFloat(obj.get("ide").toString());

                    e.setIde((int) id);
                } catch (Exception e1) {
                    System.out.println("Exception f ID");
                }

                
                  try {
                    e.setType_event(obj.get("typeEvent").toString());
                } catch (Exception e2) {
                    System.out.println("Exception f type event");
                }
                  
                try {
                    e.setChanteur(obj.get("chanteur").toString());
                } catch (Exception e3) {
                    System.out.println("Exception f Chanteur ");
                }

                try {
                    e.setAdresse(obj.get("adresse").toString());
                } catch (Exception e4) {
                    System.out.println("Exception F Adresse");
                }

              

                      try {
                          
                    float prix = Float.parseFloat(obj.get("prixE").toString());

                    e.setPrix_e((float) prix);
                } catch (Exception e5) {
                    System.out.println("Exception f prix_e");
                }
                      
                      
                try {
                    e.setImage(obj.get("image").toString());
                } catch (Exception e6) {
                    System.out.println("Exception f image ");
                }

                try {
                    Evenement.add(e);
                } catch (Exception e7) {
                    System.out.println("Exception");
                }
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return Evenement;
    }

    public ArrayList<Evenement> getAllEvenement() {
        String url =MyBd.BASE_URL + "/displayEvents";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenement = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenement;
    }

    public boolean Delete(int id) {
        String url = MyBd.BASE_URL + "/deleteEventJSON" + "?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    
    
   
     public boolean addEvenement(Evenement event) {
        String url =MyBd.BASE_URL+ "/addEventJSON?chanteur=" + event.getChanteur()+ "&Type_event=" + event.getType_event()+ "&prixE=" + event.getPrix_e()+ "&Adresse=" + event.getAdresse()+ "&image=" + event.getImage();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("Evenement == " + str);

        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

    
//    
//     public boolean addEvenement(Evenement event) {
//
//        //1
//        String addURL = Statics.BASE_URL + "/addEventJSON";
//
//        //2
//        req.setUrl(addURL);
//
//        //3
//        req.setPost(false);
//
//        //4
//        req.addArgument("chanteur", event.getChanteur());
//        req.addArgument("typeEvent", event.getType_event()+ "");
//        req.addArgument("prixE", event.getPrix_e()+ "");
//        req.addArgument("adresse", event.getAdresse()+ "");
//         req.addArgument("image", event.getImage()+ "");
//
//        //5
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return resultOK;
//    }
     
     
      
    public void modifierEvenement(Evenement event ) {
        ConnectionRequest con = new ConnectionRequest();
        String url = MyBd.BASE_URL+ "/updateEventJSON?chanteur=" + event.getChanteur()+ "&Type_event=" + event.getType_event()+ "&prixE=" + event.getPrix_e()+ "&Adresse=" + event.getAdresse()+ "&image=" + event.getImage();
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 
    
    
  
       public boolean editEvent(Evenement u) {
        System.out.println(u);
        System.out.println("********");
       String url =  MyBd.BASE_URL+ "/updateEventJSON/"+u.getIde();
       req.setUrl(url);
       req.setPost(false);
       System.out.println(url);
       
     req.addArgument("chanteur", u.getChanteur().toString());
       req.addArgument("Type_event", u.getType_event().toString());
  //     req.addArgument("prixE", Float.parseString(u.getPrix_e()));
       
       
          req.addArgument("Adresse", u.getAdresse().toString());
         req.addArgument("image", u.getImage().toString());
;
       
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
     
       
    
    
}
