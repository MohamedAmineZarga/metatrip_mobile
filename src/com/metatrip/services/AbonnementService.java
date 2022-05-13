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

public class AbonnementService {

    public static AbonnementService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Abonnement> listAbonnements;

    

    private AbonnementService() {
        cr = new ConnectionRequest();
    }

    public static AbonnementService getInstance() {
        if (instance == null) {
            instance = new AbonnementService();
        }
        return instance;
    }
    
    public ArrayList<Abonnement> getAll() {
        listAbonnements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/abonnement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listAbonnements = getList();
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

        return listAbonnements;
    }

    private ArrayList<Abonnement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Abonnement abonnement = new Abonnement(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("type"),
                        (int) Float.parseFloat(obj.get("prix").toString()),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateAchat")),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateExpiration")),
                        (String) obj.get("etat")
                        
                );

                listAbonnements.add(abonnement);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listAbonnements;
    }
    
    public int add(Abonnement abonnement) {
        return manage(abonnement, false);
    }

    public int edit(Abonnement abonnement) {
        return manage(abonnement, true );
    }

    public int manage(Abonnement abonnement, boolean isEdit) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/abonnement/edit");
            cr.addArgument("id", String.valueOf(abonnement.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/abonnement/add");
        }
        
        cr.addArgument("type", abonnement.getType());
        cr.addArgument("prix", String.valueOf(abonnement.getPrix()));
        cr.addArgument("dateAchat", new SimpleDateFormat("dd-MM-yyyy").format(abonnement.getDateAchat()));
        cr.addArgument("dateExpiration", new SimpleDateFormat("dd-MM-yyyy").format(abonnement.getDateExpiration()));
        cr.addArgument("etat", abonnement.getEtat());
        
        
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

    public int delete(int abonnementId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/abonnement/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(abonnementId));

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
