 
package com.metatrip.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
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
import com.metatrip.services.VoyageService;
import com.mycompany.entities.voyage;
import com.mycompany.entities.voyage_organise;

import java.util.HashMap;
import java.util.Map;
 

/**
 * GUI builder created Form
 *
 * @author FLAM
 */
public class VoyageUpdateGUI extends com.codename1.ui.Form {
Form current;  Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));

          public Map<String, Object> createListEntry(String name, String date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
       
    public VoyageUpdateGUI(Form previous, voyage v) {
        setTitle("Modifier un voyage");
        setLayout(BoxLayout.y());

        TextField pays = new TextField(v.getPays(), "Nom du Pays");
        TextField imagePays = new TextField(v.getImage_pays(), "Image Pays");
        
        
        
          Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));
          
          
    
  ComboBox<Map<String, Object>> combo = new ComboBox<> (
          createListEntry("Etat", "DISPO"),
          createListEntry("Etat", "INDISPO"));
    
  
  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
    hi.show();
 

 
        
        
        
                TextField idv = new TextField(String.valueOf(v.getIdv()));
        TextField prixBillet = new TextField("", "prix billet");
                TextField airline = new TextField("", "Compagnie");
                 TextField nbplaces = new TextField("", "Nombre de places");
    
        Button btnValider = new Button("Modifier");
        Button btnSupp = new Button("Supprimer");

          Button btnaddVO = new Button("Ajouter voyage organisé");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((pays.getText().length() == 0) || (imagePays.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    v.setPays(pays.getText());
                  v.setImage_pays(imagePays.getText());
             
                   VoyageService.getInstance().editVoyage(v);
                   
                                     Dialog.show("Success","voyage modifié !",new Command("OK"));
 
                }

            }

        });
            btnaddVO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                      

                if ((pays.getText().length()==0)||(imagePays.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                    
                    
                    try {
                        
                                Object y=combo.getSelectedItem().values().toArray()[1];

                        String etat=(String.valueOf(y));                           
                      Dialog.show("Alert",""+etat, new Command("OK"));

                        
                        
                        voyage_organise vo=new voyage_organise();
                        vo.setAirline(airline.getText());
                            vo.setEtatVoyage(etat);
                            vo.setPrix_billet(Float.parseFloat(prixBillet.getText().toString()));
                        vo.setNb_nuitees(0);
                        vo.setNbplaces(Integer.parseInt(nbplaces.getText().toString()));
                        vo.setIdv(Integer.parseInt(idv.getText().toString()));
                        
                        
                        
                        
                        
                        VoyageOrganiseService.getInstance().addVoyage(vo);
                          
                        Dialog.show("Success","voyage organisé ajouté !",new Command("OK"));
                    } catch (Exception ex) {
                        System.out.println("");                    }
                                     
                    
                           
                }
                
                
            }

           
        });
        
          btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoyageService.getInstance().deleteVoyage(v.getIdv());
                                     Dialog.show("Success","voyage supprimé !",new Command("OK"));

                new ListVoyagesForm(current).show();

            }

        });

        addAll(pays, imagePays,btnValider,btnSupp,nbplaces,prixBillet,airline,combo,idv,btnaddVO);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->new AccueilBack().show());

    }
}
