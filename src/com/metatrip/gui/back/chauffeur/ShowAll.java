package com.metatrip.gui.back.chauffeur;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.*;
import com.codename1.io.*;
import com.metatrip.entities.Chauffeur;
import com.metatrip.services.ChauffeurService;
import com.metatrip.utils.Statics;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class ShowAll extends Form {

    Form previous; 
    
    Resources theme = UIManager.initFirstTheme("/theme");
    
    public static Chauffeur currentChauffeur = null;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;
    

    public ShowAll(Form previous) {
        super("Chauffeurs", new BoxLayout(BoxLayout.Y_AXIS));
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
        

        ArrayList<Chauffeur> listChauffeurs = ChauffeurService.getInstance().getAll();
        componentModels = new ArrayList<>();
        
        searchTF = new TextField("", "Chercher chauffeur par Nom");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Chauffeur chauffeur : listChauffeurs) {
                if (chauffeur.getNom().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeChauffeurModel(chauffeur);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);
        
        
        if (listChauffeurs.size() > 0) {
            for (Chauffeur chauffeur : listChauffeurs) {
                Component model = makeChauffeurModel(chauffeur);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        addBtn.addActionListener(action -> {
            currentChauffeur = null;
            new Manage(this).show();
        });
        
    }
    Label nomLabel   , prenomLabel   , imageLabel   , telLabel   , descriptionLabel   , etatDispoLabel  ;
    
    ImageViewer imageIV;
    

    private Container makeModelWithoutButtons(Chauffeur chauffeur) {
        Container chauffeurModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        chauffeurModel.setUIID("containerRounded");
        
        
        nomLabel = new Label("Nom : " + chauffeur.getNom());
        nomLabel.setUIID("labelDefault");
        
        prenomLabel = new Label("Prenom : " + chauffeur.getPrenom());
        prenomLabel.setUIID("labelDefault");
        
        imageLabel = new Label("Image : " + chauffeur.getImage());
        imageLabel.setUIID("labelDefault");
        
        telLabel = new Label("Tel : " + chauffeur.getTel());
        telLabel.setUIID("labelDefault");
        
        descriptionLabel = new Label("Description : " + chauffeur.getDescription());
        descriptionLabel.setUIID("labelDefault");
        
        etatDispoLabel = new Label("EtatDispo : " + chauffeur.getEtatDispo());
        etatDispoLabel.setUIID("labelDefault");
        
        if (chauffeur.getImage() != null) {
            String url = Statics.CHAUFFEUR_IMAGE_URL + chauffeur.getImage();
            Image image = URLImage.createToStorage(
                    EncodedImage.createFromImage(theme.getImage("default.jpg").fill(1100, 500), false),
                    url,
                    url,
                    URLImage.RESIZE_SCALE
            );
            imageIV = new ImageViewer(image);
        } else {
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
        }
        imageIV.setFocusable(false);

        chauffeurModel.addAll(
                imageIV,
                nomLabel, prenomLabel, telLabel, descriptionLabel, etatDispoLabel
        );

        return chauffeurModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeChauffeurModel(Chauffeur chauffeur) {

        Container chauffeurModel = makeModelWithoutButtons(chauffeur);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentChauffeur = chauffeur;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce chauffeur ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ChauffeurService.getInstance().delete(chauffeur.getId());

                if (responseCode == 200) {
                    currentChauffeur = null;
                    dlg.dispose();
                    chauffeurModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du chauffeur. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        chauffeurModel.add(btnsContainer);

        return chauffeurModel;
    }
    
}