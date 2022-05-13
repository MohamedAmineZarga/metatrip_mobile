/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metatrip.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.metatrip.gui.back.user.AddusersForm;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
   com.codename1.ui.Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add user");
       // Button btnListTasks = new Button("List Tasks");
        
       // btnAddTask.addActionListener(e-> new AddusersForm(current).show());
        addAll(btnAddTask);
             Button btnAddTask1 = new Button("list user");
       //Button btnListTasks = new Button("List Tasks");
        
      //  btnAddTask1.addActionListener(s-> new listuser(current).show());
       Button btnValider = new Button("list user");
        btnValider.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent evt) {
                new listuser(current).show();
                
                
            }

           
        });
               Button btnValider1 = new Button("Map");
        btnValider1.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MapForm();
                
                
            }

           
        });
         
         Button btnValider2 = new Button("Reservation Voyage");
        btnValider2.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                 new ReservationVoyageForm(current).show(); 
                
            }

           
        });
         
         addAll(btnValider,btnValider1,btnValider2);
    }
    
    
}
