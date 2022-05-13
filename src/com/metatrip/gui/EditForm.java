
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;
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
import com.metatrip.entities.Evenement;
import com.metatrip.services.serviceEvenement;


/**
 *
 * @author medal
 */
public class EditForm extends com.codename1.ui.Form{
  Form current;
    public EditForm(Form previous,Evenement u) {
         current=this; //Ba
        setTitle("Edit event");
        setLayout(BoxLayout.y());
        
                
                
                    TextField chanteurTF = new TextField(u.getChanteur(), "chanteur");
        TextField type_eventTF = new TextField(u.getType_event() , "typeEvent");
    //    TextField prix_eTF = new TextField(Float.parseFloat(u.getPrix_e()).toString()  , "prixE");
        TextField adresseTF = new TextField(u.getAdresse(), "Adresse");
  //      TextField imageTF = new TextField(u.getImage() , "image");
        
        
             TextField prix_et = new TextField(u.getImage(), "image");
        
                
                
        //Calendar cld = new Calendar();
        //cld.setDate(p.getDatelancement());
        //System.out.println(u.getDatelancement());
        Button btnValider = new Button("Edit event");
         Button btnValider1 = new Button("supprimer event");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((type_eventTF.getText().length()==0)||(chanteurTF.getText().length()==0 ||(adresseTF.getText().length()==0 )))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    u.setChanteur(chanteurTF.getText().toString());
                    u.setType_event(type_eventTF.getText());
                    u.setAdresse(adresseTF.getText().toString());
 
                             
                        u.setImage(prix_et.getText());
                             
                             
                    serviceEvenement.getInstance().editEvent(u);   
                }
            }

           
        });

        
        addAll(chanteurTF,type_eventTF,adresseTF,prix_et,btnValider,btnValider1);
        
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
}
