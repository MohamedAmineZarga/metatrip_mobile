/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.metatrip.entities.reservation_voyage;
import com.metatrip.entities.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author medal
 */
public class ReservationVoyageForm extends com.codename1.ui.Form  {
    com.codename1.ui.Form current;
        private Map<String, Object> createListEntry(reservation_voyage u) {
            
        Map<String, Object> entry = new HashMap<>();
        entry.put("idu", u.getIdu());
        entry.put("dateDepart", u.getDate_arrivee());
        entry.put("dateArrivee", u.getDate_arrivee());
   entry.put("idu", u.getIdu());
    
        return entry;
    }
        
        public ReservationVoyageForm(Form previous) {
            
        current=this; //Back 

        System.out.println(ReservationVoyageService.getInstance().getAllrvs().toString());
        ArrayList<reservation_voyage> data = ReservationVoyageService.getInstance().getAllrvs();
        ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();

        for (reservation_voyage u : data) {
            dataToAdd.add(createListEntry(u));

        }
        
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(dataToAdd);
       System.out.println(model.getList());
       
        List ml = new List(model);
        ml.addActionListener(new ActionListener() {

       
            @Override
            public void actionPerformed(ActionEvent evt) {
                String res=evt.getActualComponent().toString();
                String id=res.substring(res.indexOf("selected index = ")+17);
                id=id.substring(0,id.indexOf(','));
                System.out.println(Integer.parseInt(id));
            }
        });
  addAll(ml);
        }
    
}
