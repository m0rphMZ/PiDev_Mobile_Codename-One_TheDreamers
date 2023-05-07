/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ashre
 */
    



public class Front extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public Front() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
//        ImageViewer userImage = new ImageViewer(theme.getImage("default.jpg").fill(200, 200));
     //   userImage.setUIID("candidatImage");
        label = new Label("Front");
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setUIID("buttonLogout");
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(actionConf -> {
         //   new com.athlon.gui.back.AccueilBack().show();
        });
        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
      //  userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeExerciceButton(),
                makeCourButton(),
                makeEmployeeButton(),
                makeArticleButton(),
                makeCommandeButton()
                
        );

        this.add(menuContainer);
    }

    private Button makeExerciceButton() {
        Button button = new Button("Evenement");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
       // button.addActionListener(action -> new com.athlon.gui.front.exercice.ShowAll(this).show());
        return button;
    }

    private Button makeCourButton() {
        Button button = new Button("Produit");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
     //   button.addActionListener(action -> new com.athlon.gui.front.cour.ShowAll(this).show());
        return button;
    }

    private Button makeEmployeeButton() {
        Button button = new Button("Cart");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
    //    button.addActionListener(action -> new com.athlon.gui.front.employee.ShowAll(this).show());
        return button;
    }

    private Button makeArticleButton() {
        Button button = new Button("Locaux");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
     //   button.addActionListener(action -> new com.athlon.gui.front.article.ShowAll(this).show());
        return button;
    }

    private Button makeCommandeButton() {
        Button button = new Button("Mes Commande");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
      //  button.addActionListener(action -> new com.athlon.gui.front.mesCommande.ShowAll(this).show());
        return button;
    }


}
