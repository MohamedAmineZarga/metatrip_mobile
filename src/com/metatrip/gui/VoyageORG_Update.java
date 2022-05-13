/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.services.VoyageOrganiseService;
import com.mycompany.entities.voyage;
import com.mycompany.entities.voyage_organise;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author FLAM
 */
public class VoyageORG_Update extends com.codename1.ui.Form {
Form current;  Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));

       
       
    public VoyageORG_Update(Form previous, voyage_organise vo) {
        setTitle("Modifier un voyage organisé");
        setLayout(BoxLayout.y());


               
        
                TextField idvo = new TextField(String.valueOf(vo.getIdvo()));
        TextField prixBillet = new TextField(String.valueOf(vo.getPrix_billet()), "prix billet");
                TextField airline = new TextField(vo.getAirline(), "Compagnie");
                 TextField nbplaces = new TextField(String.valueOf(vo.getNbplaces()), "Nombre de places");
            TextField etatvoyage = new TextField(vo.getEtatVoyage(), "Etat voyage");


        Button btnValider = new Button("Modifier");
        Button btnSupp = new Button("Supprimer");

         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((idvo.getText().length() == 0) || (prixBillet.getText().length() == 0) || (airline.getText().length() == 0)
                        || (etatvoyage.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    vo.setAirline(airline.getText());
               vo.setEtatVoyage(etatvoyage.getText());
               vo.setPrix_billet(Float.parseFloat(prixBillet.getText()));
               vo.setNbplaces(Integer.parseInt(nbplaces.getText()));
               vo.setNb_nuitees(0);
             
                   VoyageOrganiseService.getInstance().editVoyage(vo);
                   
                                     Dialog.show("Success","voyage organisé modifié !",new Command("OK"));
 
 
                }

            }

        });
             
        
          btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoyageOrganiseService.getInstance().deleteVoyage(vo.getIdvo());
                                     Dialog.show("Success","voyage organisé supprimé !",new Command("OK"));

                new ListVoyagesOForm(current).show();

            }

        });

        addAll(idvo, prixBillet,airline,nbplaces,etatvoyage,btnValider,btnSupp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->new AccueilBack().show() );

    }
}
