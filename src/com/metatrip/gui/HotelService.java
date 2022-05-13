package com.metatrip.gui;

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

public class HotelService {

    public static HotelService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Hotel> listHotels;

    

    private HotelService() {
        cr = new ConnectionRequest();
    }

    public static HotelService getInstance() {
        if (instance == null) {
            instance = new HotelService();
        }
        return instance;
    }
    
    public ArrayList<Hotel> getAll() {
        listHotels = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/hotel");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listHotels = getList();
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

        return listHotels;
    }

    private ArrayList<Hotel> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Hotel hotel = new Hotel(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("nom"),
                        (int) Float.parseFloat(obj.get("nbEtoiles").toString()),
                        (String) obj.get("image"),
                        (String) obj.get("adresse")
                        
                );

                listHotels.add(hotel);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return listHotels;
    }
    
    public int add(Hotel hotel) {
        return manage(hotel, false, true);
    }

    public int edit(Hotel hotel, boolean imageEdited) {
        return manage(hotel, true , imageEdited);
    }

    public int manage(Hotel hotel, boolean isEdit, boolean imageEdited) {
        
        MultipartRequest cr = new MultipartRequest();
        cr.setFilename("file", "Hotel.jpg");

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/hotel/edit");
            cr.addArgumentNoEncoding("id", String.valueOf(hotel.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/hotel/add");
        }

        if (imageEdited) {
            try {
                cr.addData("file", hotel.getImage(), "image/jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cr.addArgumentNoEncoding("image", hotel.getImage());
        }

        cr.addArgumentNoEncoding("nom", hotel.getNom());
        cr.addArgumentNoEncoding("nbEtoiles", String.valueOf(hotel.getNbEtoiles()));
        cr.addArgumentNoEncoding("image", hotel.getImage());
        cr.addArgumentNoEncoding("adresse", hotel.getAdresse());
        

        
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

    public int delete(int hotelId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/hotel/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(hotelId));

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
