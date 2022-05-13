package com.metatrip.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.metatrip.entities.Evenement;
import com.metatrip.services.serviceEvenement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author zemni
 */
public class ListArticle extends Form{
    
     Form current;
     static int nb;
     
      
    public int getNb(){return nb;}
        private Map<String, Object> createListEntry(Evenement p) {
        Map<String, Object> entry = new HashMap<>();
        
             EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 500), true);
     String url ="file:///C:/Users/FLAM/Desktop/GitNexus/metatrip_symfony/metatrip/public/"+p.getImage();
                   ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url,URLImage.RESIZE_SCALE_TO_FILL)); 
                   
                   entry.put("Line2", img.getImage());
                   
                entry.put("Line1", p.getIde());

        entry.put("Line2", p.getChanteur()+"                ");
                   entry.put("Line3", p.getAdresse()+"addresse   |      Prix      "+p.getPrix_e()+ "dt");

     
          
         entry.put("Line5", "                                                       ");
nb++;
        return entry;
        
      
    }
     
    
     public  ListArticle(Form previous) {

      
         ArrayList<Evenement> data = serviceEvenement.getInstance().getAllEvenement();

       setTitle("Liste des événements");
         
   
 
        
      
        serviceEvenement es = new serviceEvenement();
        ArrayList<Evenement> list = es.getAllEvenement();

        {
           
            for (Evenement r : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
               

                 
                 SpanLabel cat2= new SpanLabel("Type de l'événement :" + r.getType_event());
                 SpanLabel cat= new SpanLabel("Chanteur  :" + r.getChanteur());
                 SpanLabel cat1= new SpanLabel("Addresse de l'événement :" + r.getAdresse());
                 SpanLabel cat3= new SpanLabel("Prix de l'événement :" + r.getPrix_e());
          
                 
               
                     
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
       
                         Button Delete =new Button("Delete");
                         Button Edit =new Button("Edit");
         c3.add(Delete);
            c3.add(Edit);
            
            
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
               SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
              alert.add(message);
                
                
                Button ok = new Button("Confirmer");
                
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       es.Delete(r.getIde());
 

                     
                 
                     
                    }
                    
                    
                }
                
                
                );
                
        

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                new ListArticle(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
              
  add(c3);
  
         
            ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();
              
  for (Evenement p : data) {
            dataToAdd.add(createListEntry(p));
        }
  
   DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(dataToAdd);
  
     MultiList ml = new MultiList(model);
         ml.addActionListener(new ActionListener() {

       
               @Override
            public void actionPerformed(ActionEvent evt) {
                String res=evt.getActualComponent().toString();
                String ide=res.substring(res.indexOf("selected index = ")+17);
                ide=ide.substring(0,ide.indexOf(','));
                System.out.println(Integer.parseInt(ide));
                new EditForm(current,data.get(Integer.parseInt(ide))).show();
            }        });
 addAll(ml);     
 
 
            
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
          
        }
     
        
   
        
        
    }



        
    /*    
          //var
    serviceEvenement ts = serviceEvenement.getInstance();


        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tasks");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.getAllEvenement().toString());

        //end
        this.add(sl);

     }          */
 
     
      private void bindButtonselection(Button mesListes, Label arrow) {
        mesListes.addActionListener(e -> {
            if (mesListes.isSelected()) {
                updateArrowPosition(mesListes, arrow);
            }

        });
    }
      
          private void updateArrowPosition(Button mesListes, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, mesListes.getX() + mesListes.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }
          
          
          
    
    
}
