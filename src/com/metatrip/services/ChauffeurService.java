package com.metatrip.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.metatrip.entities.*;
import com.metatrip.utils.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChauffeurService {

    public static ChauffeurService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Chauffeur> listChauffeurs;

    

    private ChauffeurService() {
        cr = new ConnectionRequest();
    }

    public static ChauffeurService getInstance() {
        if (instance == null) {
            instance = new ChauffeurService();
        }
        return instance;
    }
    
    public ArrayList<Chauffeur> getAll() {
        listChauffeurs = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/chauffeur");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listChauffeurs = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listChauffeurs;
    }

    private ArrayList<Chauffeur> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Chauffeur chauffeur = new Chauffeur(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("nom"),
                        (String) obj.get("prenom"),
                        (String) obj.get("image"),
                        (String) obj.get("tel"),
                        (String) obj.get("description"),
                        (String) obj.get("etatDispo")
                        
                );

                listChauffeurs.add(chauffeur);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return listChauffeurs;
    }
    
    public int add(Chauffeur chauffeur) {
        return manage(chauffeur, false, true);
    }

    public int edit(Chauffeur chauffeur, boolean imageEdited) {
        return manage(chauffeur, true , imageEdited);
    }

    public int manage(Chauffeur chauffeur, boolean isEdit, boolean imageEdited) {
        
        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Chauffeur.jpg");

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/chauffeur/edit");
            cr.addArgumentNoEncoding("id", String.valueOf(chauffeur.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/chauffeur/add");
        }

        if (imageEdited) {
            try {
                cr.addData("file", chauffeur.getImage(), "image/jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cr.addArgumentNoEncoding("image", chauffeur.getImage());
        }

        cr.addArgumentNoEncoding("nom", chauffeur.getNom());
        cr.addArgumentNoEncoding("prenom", chauffeur.getPrenom());
        cr.addArgumentNoEncoding("image", chauffeur.getImage());
        cr.addArgumentNoEncoding("tel", chauffeur.getTel());
        cr.addArgumentNoEncoding("description", chauffeur.getDescription());
        cr.addArgumentNoEncoding("etatDispo", chauffeur.getEtatDispo());
        

        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int chauffeurId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/chauffeur/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(chauffeurId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
