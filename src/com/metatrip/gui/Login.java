package com.metatrip.gui;

import com.metatrip.MainApp;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.Resources;

public class Login extends Form {
   private Form current;
    private Resources theme;
    public static Form loginForm;

    public Login() {
        super("Connexion", new BoxLayout(BoxLayout.Y_AXIS));
        loginForm = this;
        addGUIs();
    }

    private void addGUIs() {

        
        Button frontendBtn = new Button("Front");
        frontendBtn.addActionListener(l -> new com.metatrip.gui.front.AccueilFront().show());
        this.add(frontendBtn);
        
        Button MapBtn = new Button("Map");
        MapBtn.addActionListener(l ->   new com.metatrip.gui.MapForm());

        this.add(MapBtn);
        
    
    
        
        Button backendBtn = new Button("login");
        backendBtn.addActionListener(l ->   new SignInForm().show());

        this.add(backendBtn);
        
    
    }
    
}
