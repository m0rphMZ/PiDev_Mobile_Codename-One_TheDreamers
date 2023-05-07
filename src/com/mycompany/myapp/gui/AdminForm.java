/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class AdminForm extends Form{

    public AdminForm() {
        
        setTitle("Admin Dashboard");
        setLayout(BoxLayout.y());
        
        add(new Label("Dashboard"));
        
        Button list = new Button("Listes des utilisateurs");
        Button eventAdmin = new Button("Evénements");
        Button recAdmin = new Button("Reclamations");
        
        recAdmin.addActionListener(e-> new RecBackHomeForm (this).show());
        list.addActionListener(e-> new UsersForm (this).show());
        eventAdmin.addActionListener(e-> new EventAdminHomeForm(this).show());
        
        addAll(list,eventAdmin,recAdmin);
        
        
    }
    
    
}
