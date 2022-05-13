/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metatrip.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.metatrip.entities.Evenement;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.services.serviceEvenement;
import com.codename1.ui.FontImage;


/**
 *
 * @author Z4RGA
 */

public class AddForm extends Form {

        Form current;
     


    //var
    serviceEvenement ts = serviceEvenement.getInstance();

    public AddForm() {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Event");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new AccueilBack().showBack();
        });

        //Widgets
       
        TextField chanteurTF = new TextField("", "chanteur");
        TextField type_eventTF = new TextField("", "typeEvent");
        TextField prix_eTF = new TextField("", "prixE");
        TextField adresseTF = new TextField("", "Adresse");
        TextField imageTF = new TextField("", "image");
   
        
        
        Button submitBtn = new Button("Submit");

        Evenement e = new Evenement() ;
        
        //actions
        submitBtn.addActionListener((evt) -> {
            try { 
                
                if (chanteurTF.getText().equals("") || type_eventTF.getText().equals("") || prix_eTF.getText().equals("") || adresseTF.getText().equals("")  || imageTF.getText().equals("") ) {
                    Dialog.show("Erreur", "Veuillez vérifier les données ", "Annuler", "OK");
                       } else {
                    
                     InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                     e.setChanteur(chanteurTF.getText()); 
                                e.setType_event(type_eventTF.getText());
                                e.setPrix_e(Integer.parseInt(prix_eTF.getText()));
                                e.setAdresse(adresseTF.getText());
                                e.setImage(imageTF.getText());

                    serviceEvenement.getInstance().addEvenement(e);
                    iDialog.dispose();
                          Dialog.show("Success", "Event  Inserted successfully", "Got it", null);
                            
                            
                    
                }
                
              
            }
//            if (ts.addEvenement(new Evenement(chanteurTF.getText() , type_eventTF.getText()  ,  Integer.parseInt(prix_eTF.getText()) , adresseTF.getText() , imageTF.getText()))) 
//            
//                                e.setChanteur(chanteurTF.getText()); 
//                                e.setType_event(type_eventTF.getText());
//                                e.setPrix_e(Integer.parseInt(prix_eTF.getText()));
//                                e.setAdresse(adresseTF.getText());
//                                e.setImage(imageTF.getText());
                                
            

            
//            {
//                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
//            }
            
            
               catch (Exception ex) {
            }
              
        });

        //end
        this.addAll(  chanteurTF , type_eventTF   ,prix_eTF , adresseTF , imageTF , submitBtn);

        
    }      

}
