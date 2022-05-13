package com.metatrip.gui.front.abonnement;


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

    

    Abonnement currentAbonnement;

    TextField typeTF;TextField prixTF;TextField etatTF;
    Label typeLabel;Label prixLabel;Label etatLabel;
    PickerComponent dateAchatTF;PickerComponent dateExpirationTF;
    
    
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentAbonnement == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentAbonnement = ShowAll.currentAbonnement;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        

        
        
        typeLabel = new Label("Type : ");
        typeLabel.setUIID("labelDefault");
        typeTF = new TextField();
        typeTF.setHint("Tapez le type");
        
        
        
        prixLabel = new Label("Prix : ");
        prixLabel.setUIID("labelDefault");
        prixTF = new TextField();
        prixTF.setHint("Tapez le prix");
        
        
        dateAchatTF = PickerComponent.createDate(null).label("DateAchat");
        
        
        dateExpirationTF = PickerComponent.createDate(null).label("DateExpiration");
        
        
        
        etatLabel = new Label("Etat : ");
        etatLabel.setUIID("labelDefault");
        etatTF = new TextField();
        etatTF.setHint("Tapez le etat");
        
        
        

        if (currentAbonnement == null) {
            
            
            manageButton = new Button("Ajouter");
        } else {
            typeTF.setText(currentAbonnement.getType());
            prixTF.setText(String.valueOf(currentAbonnement.getPrix()));
            dateAchatTF.getPicker().setDate(currentAbonnement.getDateAchat());
            dateExpirationTF.getPicker().setDate(currentAbonnement.getDateExpiration());
            etatTF.setText(currentAbonnement.getEtat());
            
            
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            
            typeLabel, typeTF,
            prixLabel, prixTF,
            dateAchatTF,
            dateExpirationTF,
            etatLabel, etatTF,
            
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        if (currentAbonnement == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = AbonnementService.getInstance().add(
                            new Abonnement(
                                    
                                    
                                    typeTF.getText(),
                                    (int) Float.parseFloat(prixTF.getText()),
                                    dateAchatTF.getPicker().getDate(),
                                    dateExpirationTF.getPicker().getDate(),
                                    etatTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Abonnement ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de abonnement. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = AbonnementService.getInstance().edit(
                            new Abonnement(
                                    currentAbonnement.getId(),
                                    
                                    
                                    typeTF.getText(),
                                    (int) Float.parseFloat(prixTF.getText()),
                                    dateAchatTF.getPicker().getDate(),
                                    dateExpirationTF.getPicker().getDate(),
                                    etatTF.getText()

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Abonnement modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de abonnement. Code d'erreur : " + responseCode, new Command("Ok"));
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

        
        
        if (typeTF.getText().equals("")) {
            Dialog.show("Avertissement", "Type vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (prixTF.getText().equals("")) {
            Dialog.show("Avertissement", "Prix vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", prixTF.getText() + " n'est pas un nombre valide (prix)", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        
        if (dateAchatTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dateAchat", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (dateExpirationTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dateExpiration", new Command("Ok"));
            return false;
        }
        
        
        if (etatTF.getText().equals("")) {
            Dialog.show("Avertissement", "Etat vide", new Command("Ok"));
            return false;
        }
        
        
        

        

        
             
        return true;
    }
}