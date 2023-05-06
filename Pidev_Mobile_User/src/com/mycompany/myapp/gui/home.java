/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mohamed
 */
public class home extends Form{
    public home() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Dashboard"));
        
        Button list = new Button("Profile");
        
        
        list.addActionListener(e-> new Profile (this).show());
        addAll(list);
        
        
    }
    
    
    
}
