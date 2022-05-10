/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.user;
import com.mycompany.services.UserService;

/**
 *
 * @author bhk
 */
public class AddusersForm extends Form{

    public AddusersForm(Form previous) {    
        setTitle("Add a new user");
        setLayout(BoxLayout.y());
        
        TextField cin = new TextField("","cin");
         TextField nom = new TextField("","nom");
           TextField prenom = new TextField("","prenom");
                  TextField email = new TextField("","email");
                      TextField password = new TextField("","password");
                           
                               Picker dateNaissance = new Picker();
        TextField image= new TextField("", "image");
                TextField tel= new TextField("", "tel");
        Button btnValider = new Button("Add user");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((cin.getText().length()==0)||(nom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{
          
                    try {
                     
                        user t = new user(nom.getText(),prenom.getText(),tel.getText(),email.getText(),password.getText(),image.getText(), dateNaissance.getText(),cin.getText());
                        if( UserService.getInstance().AddUser(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
         addAll(cin,nom,prenom,email,tel,password,image,dateNaissance,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
}
