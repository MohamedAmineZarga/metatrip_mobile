package com.metatrip.gui.back;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.metatrip.gui.AddForm;
import com.metatrip.gui.ListArticle;
import com.metatrip.gui.ListVoyagesForm;
import com.metatrip.gui.ListVoyagesOForm;
import com.metatrip.gui.Login;
import com.metatrip.gui.StatistiquePieForm;
import com.metatrip.gui.Statzarga;
import com.metatrip.gui.VoyageAddGUI;
import com.metatrip.gui.back.user.AddusersForm;
import com.metatrip.gui.listuser;

public class AccueilBack extends Form {
Resources res;
   com.codename1.ui.Form current;
    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("default.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setUIID("buttonLogout");
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            Login.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeHotelsButton(), 
                makeChauffeursButton(), 
                makeAbonnementsButton(),
                makeuserButton(),
                  makelistvoyageButton(),  makeaddvoyageButton(), StatistiquePieButton(),VoyageOButton(),    addEventbutton() ,Eventbutton(),statEventbutton()
        );

        this.add(menuContainer);
    }

    private Button makeHotelsButton() {
        Button button = new Button("Hotels");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.metatrip.gui.back.hotel.ShowAll(this).show());
        return button;
    }
    private Button makeChauffeursButton() {
        Button button = new Button("Chauffeurs");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.metatrip.gui.back.chauffeur.ShowAll(this).show());
        return button;
    }
    private Button makeAbonnementsButton() {
        Button button = new Button("Abonnements");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.metatrip.gui.back.abonnement.ShowAll(this).show());
        return button;
    }
        private Button makeuserButton() {
        Button button = new Button("user");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new  listuser(current).show());
        return button;
    }
          private Button makelistvoyageButton() {
        Button button = new Button("voyage ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->   new ListVoyagesForm(current).show());
        return button;
    } 
      
         private Button makeaddvoyageButton() {
        Button button = new Button("add voyage ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->   new VoyageAddGUI(current).show());
        return button;
    } 
        private Button StatistiquePieButton() {
        Button button = new Button("Statistique voyage ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->   new StatistiquePieForm(res).show());
        return button;
    }   
          private Button VoyageOButton() {
        Button button = new Button(" voyage Organisé ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->   new ListVoyagesOForm(current).show());
        return button;
    }  
      
             private Button Eventbutton() {
        Button button = new Button(" list  Event ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->  new ListArticle(current).show());
        return button;
    }  
                  private Button addEventbutton() {
        Button button = new Button(" add Event ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
      //  button.addActionListener(action ->  new AddForm(current).show());
        return button;
    }  
                   private Button statEventbutton() {
        Button button = new Button(" list  Event ");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action ->  new Statzarga(res).show());
        return button;
    }  
           
          
       // btnListTasks.addActionListener(e-> new ListVoyagesForm(current).show());
//                btnListVOTasks.addActionListener(e-> new ListVoyagesOForm(current).show());

        
  //              btnAddTask.addActionListener(e-> new VoyageAddGUI(current).show());
     //          btnStat.addActionListener(e-> new StatistiquePieForm(res).show());

}
