 
package com.metatrip.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.metatrip.gui.back.AccueilBack;
import com.metatrip.services.VoyageOrganiseService;
import com.mycompany.entities.voyage;
import com.mycompany.entities.voyage_organise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author bhk
 */
public class ListVoyagesOForm extends com.codename1.ui.Form {
 Form current;
 static int nb;
 
    public int getNb(){return nb;}
        private Map<String, Object> createListEntry(voyage_organise p) {
        Map<String, Object> entry = new HashMap<>();
                entry.put("Line1", p.getIdvo());

        entry.put("Line2", p.getAirline()+"                ");
                   entry.put("Line3", p.getNbplaces()+"places   |      Prix Billet     "+p.getPrix_billet()+ "dt");

        entry.put("Line4", "Status   "+p.getEtatVoyage());
          
         entry.put("Line5", "                                                       ");
nb++;
        return entry;
        
      
    }

    public ListVoyagesOForm(Form previous) {
        current=this; //Back

        System.out.println(VoyageOrganiseService.getInstance().getAllTasks().toString());
        ArrayList<voyage_organise> data = VoyageOrganiseService.getInstance().getAllTasks();
        ArrayList<Map<String, Object>> dataToAdd = new ArrayList<>();

        for (voyage_organise p : data) {
            dataToAdd.add(createListEntry(p));
        }
        System.out.println("nombre"+nb);
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(dataToAdd);

        MultiList ml = new MultiList(model);
         ml.addActionListener(new ActionListener() {

       
               @Override
            public void actionPerformed(ActionEvent evt) {
                String res=evt.getActualComponent().toString();
                String idvo=res.substring(res.indexOf("selected index = ")+17);
                idvo=idvo.substring(0,idvo.indexOf(','));
                System.out.println(Integer.parseInt(idvo));
                new VoyageORG_Update(current,data.get(Integer.parseInt(idvo))).show();
            }        });
 addAll(ml);     
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new AccueilBack().show());

    }

   
}
