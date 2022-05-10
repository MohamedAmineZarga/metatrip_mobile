/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.mycompany.entities.user;
import com.mycompany.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author medal
 */
public class listuser  extends com.codename1.ui.Form  {
    com.codename1.ui.Form current;
        private Map<String, Object> createListEntry(user u) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("idu", u.getIdu());
        entry.put("cin", u.getCin());
        entry.put("email", u.getEmail());
   entry.put("password", u.getPassword());
      entry.put("tel", u.getTel());
          entry.put("image", u.getImage());
           entry.put("datedenaissance", u.getDateNaissance());
        return entry;
    }
        
        public listuser() {
        current=this; //Back 

        System.out.println(UserService.getInstance().getAllusers().toString());
        ArrayList<user> data = UserService.getInstance().getAllusers();
        ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();

        for (user u : data) {
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
               new EdituserForm(current,data.get(Integer.parseInt(id))).show();
            }
        });
    add(ml);
    }
    
}
