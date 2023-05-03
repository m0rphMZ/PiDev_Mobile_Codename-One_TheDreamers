/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.demo.gui;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.demo.entities.Event;
import com.mycompany.demo.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class EventsListForm extends Form{
    public EventsListForm(Form previous) {
        setTitle("Events list");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Event> events = ServiceEvent.getInstance().getAllEvents();
        for (Event e : events) {
            addElement(e);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Event ev) {
        Container SingleEventContainer = new Container(BoxLayout.y());
        SingleEventContainer.setUIID("EventContainer");
        
        Label TitleValue = new Label(ev.getTitle());
        TitleValue.setUIID("TitleValue");
        Label TypeLabel = new Label("Type:");
        Label TypeValue = new Label(ev.getType());
        Label DescLabel = new Label("Description:");
        Label DescValue = new Label(ev.getDescription());
        
        Container TitleContainer = new Container(BoxLayout.x());
        TitleContainer.setUIID("TitleContainer");

        Container SingleEventContent = new Container(BoxLayout.y());
        SingleEventContent.setUIID("SEContent");

        TitleContainer.addAll(TitleValue);

        SingleEventContent.addAll(TitleContainer, TypeLabel, TypeValue, DescLabel, DescValue);
        SingleEventContent.getAllStyles().setMarginTop(5);

        SingleEventContainer.addAll(SingleEventContent);

        add(SingleEventContainer);

    }
}
