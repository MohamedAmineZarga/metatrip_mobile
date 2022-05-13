 
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
import com.mycompany.entities.voyage;

 import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.services.VoyageService;
 import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
 
/**
 * GUI builder created Form
 *
 * @author FLAM
 */
public class VoyageAddGUI extends com.codename1.ui.Form {
   
               static Object x;
                        static List<voyage> lista=VoyageService.getInstance().getAllTasks(); 
               
             /*   
static Map<String, Object> createListEntry() {
    Map<String, Object> entry = new HashMap<>();
          for (int i=0;i<lista.size();i++){

    entry.put("Line1", lista.get(i).getPays());
    entry.put("Line2", lista.get(i).getImage_pays());
}
    return entry;
}
      
    */
     public VoyageAddGUI(Form previous) {
         

         
        setTitle("Ajouter un voyage");
        setLayout(BoxLayout.y());
       
        TextField pays = new TextField(""," Pays");
        TextField imagePays= new TextField("", "Image pays");
        
   /*
          ComboBox<Map<String, Object>> combo = new ComboBox<> (
   VoyageAddGUI.createListEntry()
          
          );
  
  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
 
 

        */
        
        
     
         Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                                 //   Object y=combo.getSelectedItem().values().toArray()[1];

                      //  int noumrou=Integer.parseInt(String.valueOf(y));                           
                       // Dialog.show("Alert",""+noumrou, new Command("OK"));


                if ((pays.getText().length()==0)||(imagePays.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                    
                    
                    try {
                        voyage v=new voyage(pays.getText().toString(),imagePays.getText().toString());
                        
                        
                        
                        
                        
                        VoyageService.getInstance().addVoyage(v);
                          
                        Dialog.show("Success","voyage ajouté !",new Command("OK"));
                    } catch (Exception ex) {
                        System.out.println("");                    }
                                     
                    
                           
                }
                
                
            }

           
        });
        
        addAll(pays,imagePays,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new AccueilBack().show() );
                
    }
}
