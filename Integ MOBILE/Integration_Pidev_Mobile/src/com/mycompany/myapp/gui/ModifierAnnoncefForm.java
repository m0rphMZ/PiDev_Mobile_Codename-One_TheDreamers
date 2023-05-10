package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.myapp.entities.Produit1;

import com.mycompany.myapp.gui.ListProduittForm;
import com.mycompany.myapp.services.ServiceProduit;

public class ModifierAnnoncefForm extends Form {
    private Form previous;
    private Produit1 annoncef;

    private TextField tfnom;
    private TextField tftype;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierAnnoncefForm(Form previous, Produit1 annoncef) {
        super("Modifier annoncef", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tfnom = new TextField(annoncef.getCode(), "Nom du Produit");
        tftype = new TextField(annoncef.getDes(), "Adresse du Produit");
       
       

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setCode(tfnom.getText());
            annoncef.setDes(tftype.getText());
           
            

            if (ServiceProduit.getInstance().modifierEvenement(annoncef)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListProduittForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListProduittForm(previous).show());
        addAll(tfnom, tftype, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}
