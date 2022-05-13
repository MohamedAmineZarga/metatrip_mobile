package com.metatrip.gui.back.hotel;


import com.metatrip.gui.HotelService;
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
    

    Hotel currentHotel;

    TextField nomTF;TextField nbEtoilesTF;TextField imageTF;TextField adresseTF;
    Label nomLabel;Label nbEtoilesLabel;Label imageLabel;Label adresseLabel;
    
    
    
    
    ImageViewer imageIV;
    Button selectImageButton;
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentHotel == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentHotel = ShowAll.currentHotel;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        

        
        
        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");
        
        
        
        nbEtoilesLabel = new Label("NbEtoiles : ");
        nbEtoilesLabel.setUIID("labelDefault");
        nbEtoilesTF = new TextField();
        nbEtoilesTF.setHint("Tapez le nbEtoiles");
        
        
        
        
        
        
        
        adresseLabel = new Label("Adresse : ");
        adresseLabel.setUIID("labelDefault");
        adresseTF = new TextField();
        adresseTF.setHint("Tapez le adresse");
        
        
        
        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");

        if (currentHotel == null) {
            
            imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));
            
            
            manageButton = new Button("Ajouter");
        } else {
            nomTF.setText(currentHotel.getNom());
            nbEtoilesTF.setText(String.valueOf(currentHotel.getNbEtoiles()));
            
            adresseTF.setText(currentHotel.getAdresse());
            
            
            
            if (currentHotel.getImage() != null) {
                selectedImage = currentHotel.getImage();
                String url = Statics.HOTEL_IMAGE_URL + currentHotel.getImage();
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
            nbEtoilesLabel, nbEtoilesTF,
            
            adresseLabel, adresseTF,
            
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
        
        if (currentHotel == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = HotelService.getInstance().add(
                            new Hotel(
                                    
                                    
                                    nomTF.getText(),
                                    (int) Float.parseFloat(nbEtoilesTF.getText()),
                                    selectedImage,
                                    adresseTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Hotel ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de hotel. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = HotelService.getInstance().edit(
                            new Hotel(
                                    currentHotel.getId(),
                                    
                                    
                                    nomTF.getText(),
                                    (int) Float.parseFloat(nbEtoilesTF.getText()),
                                    selectedImage,
                                    adresseTF.getText()

                            ), imageEdited
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Hotel modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de hotel. Code d'erreur : " + responseCode, new Command("Ok"));
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
        
        
        
        
        if (nbEtoilesTF.getText().equals("")) {
            Dialog.show("Avertissement", "NbEtoiles vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbEtoilesTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbEtoilesTF.getText() + " n'est pas un nombre valide (nbEtoiles)", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        
        
        if (adresseTF.getText().equals("")) {
            Dialog.show("Avertissement", "Adresse vide", new Command("Ok"));
            return false;
        }
        
        
        

        

        
        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }
        
             
        return true;
    }
}