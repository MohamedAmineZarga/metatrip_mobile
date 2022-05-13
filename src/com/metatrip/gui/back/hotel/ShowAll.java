package com.metatrip.gui.back.hotel;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.*;
import com.codename1.io.*;
import com.metatrip.entities.Hotel;
import com.metatrip.gui.HotelService;
import com.metatrip.utils.Statics;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class ShowAll extends Form {

    Form previous; 
    
    Resources theme = UIManager.initFirstTheme("/theme");
    
    public static Hotel currentHotel = null;
    Button addBtn;

    
    PickerComponent sortPicker;
    ArrayList<Component> componentModels;

    public ShowAll(Form previous) {
        super("Hotels", new BoxLayout(BoxLayout.Y_AXIS));
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
        

        ArrayList<Hotel> listHotels = HotelService.getInstance().getAll();
        
        componentModels = new ArrayList<>();
        
        sortPicker = PickerComponent.createStrings("Nom", "NbEtoiles", "Adresse").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listHotels);
            for (Hotel hotel : listHotels) {
                Component model = makeHotelModel(hotel);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);
        
        if (listHotels.size() > 0) {
            for (Hotel hotel : listHotels) {
                Component model = makeHotelModel(hotel);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        addBtn.addActionListener(action -> {
            currentHotel = null;
            new Manage(this).show();
        });
        
    }
    Label nomLabel   , nbEtoilesLabel   , imageLabel   , adresseLabel  ;
    
    ImageViewer imageIV;
    

    private Container makeModelWithoutButtons(Hotel hotel) {
        Container hotelModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        hotelModel.setUIID("containerRounded");
        
        
        nomLabel = new Label("Nom : " + hotel.getNom());
        nomLabel.setUIID("labelDefault");
        
        nbEtoilesLabel = new Label("NbEtoiles : " + hotel.getNbEtoiles());
        nbEtoilesLabel.setUIID("labelDefault");
        
        imageLabel = new Label("Image : " + hotel.getImage());
        imageLabel.setUIID("labelDefault");
        
        adresseLabel = new Label("Adresse : " + hotel.getAdresse());
        adresseLabel.setUIID("labelDefault");
        
        if (hotel.getImage() != null) {
            String url = Statics.HOTEL_IMAGE_URL + hotel.getImage();
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

        hotelModel.addAll(
                imageIV,
                nomLabel, nbEtoilesLabel, adresseLabel
        );

        return hotelModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeHotelModel(Hotel hotel) {

        Container hotelModel = makeModelWithoutButtons(hotel);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentHotel = hotel;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce hotel ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = HotelService.getInstance().delete(hotel.getId());

                if (responseCode == 200) {
                    currentHotel = null;
                    dlg.dispose();
                    hotelModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du hotel. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        hotelModel.add(btnsContainer);

        return hotelModel;
    }
    
}