package com.metatrip.gui.back.user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.metatrip.entities.user;
import com.metatrip.services.UserService;

/**
 *
 * @author medal
 */
public class EdituserForm extends com.codename1.ui.Form{
  Form current;
    public EdituserForm(Form previous,user u) {
         current=this; //Ba
        setTitle("Edit user");
        setLayout(BoxLayout.y());
        
        TextField cin = new TextField(u.getCin(),"cin");
        TextField tfEmail= new TextField(u.getEmail(), "Email");
          TextField password= new TextField(u.getPassword(), "password");
               TextField image= new TextField(u.getImage(), "image");
         TextField nom = new TextField(u.getImage(),"nom");
           TextField prenom = new TextField(u.getPrenom(),"prenom");
                               Picker dateNaissance = new Picker();
                TextField tel= new TextField(u.getTel(), "tel");
        //Calendar cld = new Calendar();
        //cld.setDate(p.getDatelancement());
        //System.out.println(u.getDatelancement());
        Button btnValider = new Button("Edit user");
         Button btnValider1 = new Button("supprimer user");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((cin.getText().length()==0)||(tfEmail.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    u.setCin(cin.getText().toString());
                    u.setEmail(tfEmail.getText());
                    u.setDateNaissance(dateNaissance.getText().toString());
                    u.setPassword(password.getText().toString());
                     u.setNom(nom.getText().toString());
                           u.setPrenom(prenom.getText().toString());
                             u.setImage(image.getText().toString());
                    UserService.getInstance().editUser(u);   
                }
            }

           
        });
           btnValider1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          
                    UserService.getInstance().supprimeruser(u);   
            
            }

           
        });
        
        addAll(cin,prenom,tfEmail,password,image,dateNaissance,btnValider,btnValider1);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
