package com.metatrip.gui.back.chauffeur;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.metatrip.entities.*;
import com.metatrip.services.*;
import com.metatrip.utils.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manage extends Form {

    
    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;
    

    Chauffeur currentChauffeur;

    TextField nomTF;TextField prenomTF;TextField imageTF;TextField telTF;TextField descriptionTF;TextField etatDispoTF;
    Label nomLabel;Label prenomLabel;Label imageLabel;Label telLabel;Label descriptionLabel;Label etatDispoLabel;
    
    
    
    
    ImageViewer imageIV;
    Button selectImageButton;
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentChauffeur == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentChauffeur = ShowAll.currentChauffeur;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        

        
        
        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");
        
        
        
        prenomLabel = new Label("Prenom : ");
        prenomLabel.setUIID("labelDefault");
        prenomTF = new TextField();
        prenomTF.setHint("Tapez le prenom");
        
        
        
        
        
        
        
        telLabel = new Label("Tel : ");
        telLabel.setUIID("labelDefault");
        telTF = new TextField();
        telTF.setHint("Tapez le tel");
        
        
        
        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");
        
        
        
        etatDispoLabel = new Label("EtatDispo : ");
        etatDispoLabel.setUIID("labelDefault");
        etatDispoTF = new TextField();
        etatDispoTF.setHint("Tapez le etatDispo");
        
        
        
        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");

        if (currentChauffeur == null) {
            
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
            
            
            manageButton = new Button("Ajouter");
        } else {
            nomTF.setText(currentChauffeur.getNom());
            prenomTF.setText(currentChauffeur.getPrenom());
            
            telTF.setText(currentChauffeur.getTel());
            descriptionTF.setText(currentChauffeur.getDescription());
            etatDispoTF.setText(currentChauffeur.getEtatDispo());
            
            
            
            if (currentChauffeur.getImage() != null) {
                selectedImage = currentChauffeur.getImage();
                String url = Statics.CHAUFFEUR_IMAGE_URL + currentChauffeur.getImage();
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
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            imageLabel, imageIV,
            selectImageButton,
            nomLabel, nomTF,
            prenomLabel, prenomTF,
            
            telLabel, telTF,
            descriptionLabel, descriptionTF,
            etatDispoLabel, etatDispoTF,
            
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });
        
        if (currentChauffeur == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ChauffeurService.getInstance().add(
                            new Chauffeur(
                                    
                                    
                                    nomTF.getText(),
                                    prenomTF.getText(),
                                    selectedImage,
                                    telTF.getText(),
                                    descriptionTF.getText(),
                                    etatDispoTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Chauffeur ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de chauffeur. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ChauffeurService.getInstance().edit(
                            new Chauffeur(
                                    currentChauffeur.getId(),
                                    
                                    
                                    nomTF.getText(),
                                    prenomTF.getText(),
                                    selectedImage,
                                    telTF.getText(),
                                    descriptionTF.getText(),
                                    etatDispoTF.getText()

                            ), imageEdited
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Chauffeur modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de chauffeur. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh(){
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {

        
        
        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Nom vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (prenomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Prenom vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        
        
        if (telTF.getText().equals("")) {
            Dialog.show("Avertissement", "Tel vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (etatDispoTF.getText().equals("")) {
            Dialog.show("Avertissement", "EtatDispo vide", new Command("Ok"));
            return false;
        }
        
        
        

        

        
        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }
        
             
        return true;
    }
}