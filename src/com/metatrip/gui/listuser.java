/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;

import com.metatrip.gui.back.user.EdituserForm;
import com.metatrip.gui.back.user.AddusersForm;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
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
import com.metatrip.entities.user;

import com.codename1.ui.list.MultiList;
import static com.metatrip.gui.ListVoyagesOForm.nb;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.services.UserService;
import com.mycompany.entities.voyage_organise;
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
                entry.put("Line1", u.getIdu());

       
              //     entry.put("Line2","   Prrenom    "+u.getPrenom()+ "");

        entry.put("Line2", "email   "+u.getEmail());
          
         entry.put("Line4", "                                                       ");
nb++;
        return entry;
        
      
    }
        
        public listuser(Form previous) {
            
        current=this; //Back 
Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddusersForm(current).show();
                
                
            }

           
        });
        System.out.println(UserService.getInstance().getAllusers().toString());
        ArrayList<user> data = UserService.getInstance().getAllusers();
        ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();

        for (user u : data) {
            dataToAdd.add(createListEntry(u));

        }
        
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(dataToAdd);
       System.out.println(model.getList());
       
              MultiList ml = new MultiList(model);
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
  addAll(btnValider,ml);
   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new AccueilBack().show());
        }
    
}
