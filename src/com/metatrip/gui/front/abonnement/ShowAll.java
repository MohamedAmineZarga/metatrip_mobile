package com.metatrip.gui.front.abonnement;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.*;
import com.codename1.io.*;
import com.metatrip.entities.Abonnement;
import com.metatrip.services.AbonnementService;
import com.metatrip.utils.Statics;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class ShowAll extends Form {

    Form previous; 
    
    public static Abonnement currentAbonnement = null;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;
    

    public ShowAll(Form previous) {
        super("Abonnements", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);
        

        ArrayList<Abonnement> listAbonnements = AbonnementService.getInstance().getAll();
        componentModels = new ArrayList<>();
        
        searchTF = new TextField("", "Chercher abonnement par Type");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Abonnement abonnement : listAbonnements) {
                if (abonnement.getType().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeAbonnementModel(abonnement);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);
        
        
        if (listAbonnements.size() > 0) {
            for (Abonnement abonnement : listAbonnements) {
                Component model = makeAbonnementModel(abonnement);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        addBtn.addActionListener(action -> {
            currentAbonnement = null;
            new Manage(this).show();
        });
        
    }
    Label typeLabel   , prixLabel   , dateAchatLabel   , dateExpirationLabel   , etatLabel  ;
    

    private Container makeModelWithoutButtons(Abonnement abonnement) {
        Container abonnementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        abonnementModel.setUIID("containerRounded");
        
        
        typeLabel = new Label("Type : " + abonnement.getType());
        typeLabel.setUIID("labelDefault");
        
        prixLabel = new Label("Prix : " + abonnement.getPrix());
        prixLabel.setUIID("labelDefault");
        
        dateAchatLabel = new Label("DateAchat : " + new SimpleDateFormat("dd-MM-yyyy").format(abonnement.getDateAchat()));
        dateAchatLabel.setUIID("labelDefault");
        
        dateExpirationLabel = new Label("DateExpiration : " + new SimpleDateFormat("dd-MM-yyyy").format(abonnement.getDateExpiration()));
        dateExpirationLabel.setUIID("labelDefault");
        
        etatLabel = new Label("Etat : " + abonnement.getEtat());
        etatLabel.setUIID("labelDefault");
        

        abonnementModel.addAll(
                
                typeLabel, prixLabel, dateAchatLabel, dateExpirationLabel, etatLabel
        );

        return abonnementModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeAbonnementModel(Abonnement abonnement) {

        Container abonnementModel = makeModelWithoutButtons(abonnement);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentAbonnement = abonnement;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce abonnement ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = AbonnementService.getInstance().delete(abonnement.getId());

                if (responseCode == 200) {
                    currentAbonnement = null;
                    dlg.dispose();
                    abonnementModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du abonnement. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        abonnementModel.add(btnsContainer);

        return abonnementModel;
    }
    
}