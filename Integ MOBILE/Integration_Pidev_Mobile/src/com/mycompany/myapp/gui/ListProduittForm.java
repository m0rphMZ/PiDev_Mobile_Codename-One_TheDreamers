/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import com.mycompany.myapp.entities.Produit1;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author monta
 */
public class ListProduittForm extends Form {
private Form previous;
public ListProduittForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des Produit");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<Produit1> annonces = ServiceProduit.getInstance().getAllEvenement();
    
    for (Produit1 annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label nomLabel = new Label("Nom : " + annonce.getCode());
        Label adresseLabel = new Label("Description : " + annonce.getDes());
        //     Label descriptionLabel = new Label("Description : " + annonce.getDescription_produit());
          Label PRIX = new Label("PRIX : " + annonce.getPrix_produit());


       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette Produit ?", "Oui", "Non")) {
        if (ServiceProduit.getInstance().deleteEvenement(annonce.getId_produit())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'Produit", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierAnnoncefForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(adresseLabel);
            container.add(PRIX);
        
        
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddProduitForm(this).show());
}
}