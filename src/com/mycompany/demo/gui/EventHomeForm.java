/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.demo.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Aymen
 */
public class EventHomeForm extends Form{
    public EventHomeForm() {    
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Event");
        Button btnListTasks = new Button("List Events");
        
        //btnAddTask.addActionListener(e-> new AddTaskForm(this).show());
        btnListTasks.addActionListener(e-> new EventsListForm(this).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
}
