/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gui.AddusersForm;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
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
       
         addAll(btnAddTask1);
    }
    
    
}
