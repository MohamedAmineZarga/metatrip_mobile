
package com.metatrip.services;
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
import com.metatrip.entities.user;

 /**
 *
 * @author medal
 */
public class inscriptionForm extends Form{
 
    public inscriptionForm(Form previous) {    
     
         setTitle("Inscription");
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
                        UserService.getInstance().inscription(t);
                      
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
