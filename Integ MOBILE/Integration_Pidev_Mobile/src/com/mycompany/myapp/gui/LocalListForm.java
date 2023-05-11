/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.mycompany.myapp.entities.CategorieLocal;
import com.mycompany.myapp.entities.Local;
import com.mycompany.myapp.services.LocalService;

/**
 *
 * @author Administrateur
 */
public class LocalListForm extends Form {

    private final ArrayList<Local> locaux;
    private final ArrayList<CategorieLocal> catgo;

    public LocalListForm(Form previous) {
        super("Locaux List");
        Toolbar toolbar = getToolbar();
        toolbar.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getContentPane().setScrollVisible(false);

        // Get all the evenements from the server
        locaux = LocalService.getInstance().getAllLocaux();

        Button ajout = new Button("Ajouter un nouveau Local");

        ajout.addActionListener(b -> {
            LocalAddForm a=new LocalAddForm();
            a.show();
        });
         Button stat = new Button("statistique");

        stat.addActionListener(b -> {
           Statistic form1=new Statistic(this);
          form1.show();
        });
          // Get all the evenements from the server
        catgo = LocalService.getInstance().getAllCategories();

        Button ajout1 = new Button("gerer categorie");

        ajout1.addActionListener(b -> {
            CategorieList c=new CategorieList();
            c.showList();
        });
        
        
           




        
           Button tri = new Button("trie par lieu");
        Container evenementContainer = new Container();
        evenementContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tri.addActionListener(b -> {
          //  CategorieList c=new CategorieList();
           // c.show();
           evenementContainer.removeAll();
           
            evenementContainer.revalidate();
evenementContainer.repaint();

                Collections.sort(locaux, (Local o1,Local o2)-> o1.getLieu().compareTo(o2.getLieu()));
           System.out.println("hello");  
           for (Local local : locaux) {

            // Create a container to hold the evenement's information and buttons
            Container evenementRow = new Container(new BorderLayout());
            evenementRow.setUIID("UserBox");

            // Create labels to display the evenement's information
            Label num_localLabel = new Label("id_local: " + local.getNum());
            num_localLabel.setUIID("num_localLabel");

            Label descript_localLabel = new Label("descript_local: " + local.getDescript());
            descript_localLabel.setUIID("descript_localLabel");

            Label lieu_localLabel = new Label("lieu_local: " + local.getLieu());
            lieu_localLabel.setUIID("lieu_localLabel");

             Label date_localLabel = new Label("date_local: " + local.getDate());
            date_localLabel.setUIID("date_localLabel");
            Label surface_localLabel = new Label("surface_local: " + local.getSurface());
            surface_localLabel.setUIID("surface_localLabel");

            Label nbpers_localLabel = new Label("nbpers_local: " + local.getNbper());
            nbpers_localLabel.setUIID("nbpers_localLabel");
            
             Label prix_localLabel = new Label("prix_local: " + local.getPrix());
            prix_localLabel.setUIID("prix_localLabel");
             Label equipements_localLabel = new Label("equipements_local: " + local.getEquipements());
            equipements_localLabel.setUIID("lieu_localLabel");
            Label disponibilite_localLabel = new Label("disponibilte_local: " + local.getDisponibilite());
            disponibilite_localLabel.setUIID("disponibilite_localLabel");
            
            

            Label categorie_idLabel = new Label("categorie_id: " + local.getCodec());
            categorie_idLabel.setUIID("utilisateur_idLabel");

            Label separator = new Label("******");
            categorie_idLabel.setUIID("separator");

            /* Label imageLabel = new Label("image: " + evenement.getPrix_evenement());
            imageLabel.setUIID("imageLabel");*/
            System.out.println("test");

            // Create buttons to reserve, edit, and delete the evenement
//            Button reserveBtn = new Button("Add");
//            reserveBtn.addActionListener(e -> {
//          //  Reserve the evenement
//           });
//            Button deleteBtn = new Button("Delete");
//            deleteBtn.addActionListener(a -> {
//                //evenementService.getInstance().deleteEvenement(evenement.getId_evenement());
//
//                evenementContainer.removeComponent(evenementRow);
//            });
            // add(remove);
//evenementContainer.removeComponent(evenementRow);

Button remove = new Button("remove");
                System.out.println(local.getNum());
            remove.addActionListener(e -> {

              LocalService.getInstance().deleteLocal(local.getNum());
                 LocalListForm a = new LocalListForm(this);
                a.show();
                
                evenementContainer.removeComponent(evenementRow);
            });
            //evenementcontainer.removeAll()
         
            Button modifier = new Button("modifier");
            //modifier.addActionListener(e -> {
              //   modifierLocal h = new modifierEvenement(res,current,p);
                //h.show();
             modifier.addActionListener(e-> new UpdateLocalForm(this, local.getNum()).show());
                
                
          //  });
Button pdf=new Button("PDF");
    pdf.addActionListener((ActionEvent m) -> {
    try {
        Document document = new Document() {};
        String outputPath = "file:///C:/xampp/htdocs/ImageP/offre" + local.getNum() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        document.open();

        document.add(new Paragraph("Fiche des Locaux "));
        document.add(new Paragraph("Description :" + local.getDescript()));
        document.add(new Paragraph("Lieu :" + local.getLieu()));
         document.add(new Paragraph("surface :" +local.getSurface()));
          document.add(new Paragraph("Capacite :" + local.getNbper()));
        

        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});


            // Add the labels to the evenement row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(num_localLabel);
            labelsContainer.add(descript_localLabel);
            labelsContainer.add(lieu_localLabel);
            labelsContainer.add(surface_localLabel);
            labelsContainer.add(nbpers_localLabel);
             labelsContainer.add(prix_localLabel);
              labelsContainer.add(equipements_localLabel);
               labelsContainer.add(disponibilite_localLabel);
                labelsContainer.add(date_localLabel);
            labelsContainer.add(categorie_idLabel);
            
            labelsContainer.add(separator);
            

            /*labelsContainer.add(description_evenementLabel);
            labelsContainer.add(utilisateur_idLabel);
            labelsContainer.add(imageLabel);
            labelsContainer.add(deleteBtn);*/
            evenementRow.add(BorderLayout.CENTER, labelsContainer);

//           //  Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("evenementButtonBox");

            
            //    add(modifier);

            buttonsContainer.add(remove);
            buttonsContainer.add(modifier);
            buttonsContainer.add(pdf);
//           // buttonsContainer.add(deleteBtn);
//         
            evenementRow.add(BorderLayout.EAST, buttonsContainer);

            // Add the evenement row to the container
            evenementContainer.add(evenementRow);
               evenementContainer.revalidate();
evenementContainer.repaint();

        }
           
           
           
           
           
    
        });
        
        
        
        
        
        
        
        
        
        
        
        Button tri2 = new Button("trie par capacite");
       // Container evenementContainer = new Container();
        evenementContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tri2.addActionListener(b -> {
          //  CategorieList c=new CategorieList();
           // c.show();
           evenementContainer.removeAll();
           
            evenementContainer.revalidate();
evenementContainer.repaint();

                Collections.sort(locaux, (Local o1,Local o2)-> o1.getNbper()-o2.getNbper());
           System.out.println("hello");  
           for (Local local : locaux) {

            // Create a container to hold the evenement's information and buttons
            Container evenementRow = new Container(new BorderLayout());
            evenementRow.setUIID("UserBox");

            // Create labels to display the evenement's information
            Label num_localLabel = new Label("id_local: " + local.getNum());
            num_localLabel.setUIID("num_localLabel");

            Label descript_localLabel = new Label("descript_local: " + local.getDescript());
            descript_localLabel.setUIID("descript_localLabel");

            Label lieu_localLabel = new Label("lieu_local: " + local.getLieu());
            lieu_localLabel.setUIID("lieu_localLabel");

             Label date_localLabel = new Label("date_local: " + local.getDate());
            date_localLabel.setUIID("date_localLabel");
            Label surface_localLabel = new Label("surface_local: " + local.getSurface());
            surface_localLabel.setUIID("surface_localLabel");

            Label nbpers_localLabel = new Label("nbpers_local: " + local.getNbper());
            nbpers_localLabel.setUIID("nbpers_localLabel");
            
             Label prix_localLabel = new Label("prix_local: " + local.getPrix());
            prix_localLabel.setUIID("prix_localLabel");
             Label equipements_localLabel = new Label("equipements_local: " + local.getEquipements());
            equipements_localLabel.setUIID("lieu_localLabel");
            Label disponibilite_localLabel = new Label("disponibilte_local: " + local.getDisponibilite());
            disponibilite_localLabel.setUIID("disponibilite_localLabel");
            
            

            Label categorie_idLabel = new Label("categorie_id: " + local.getCodec());
            categorie_idLabel.setUIID("utilisateur_idLabel");

            Label separator = new Label("******");
            categorie_idLabel.setUIID("separator");

            /* Label imageLabel = new Label("image: " + evenement.getPrix_evenement());
            imageLabel.setUIID("imageLabel");*/
            System.out.println("test");

            // Create buttons to reserve, edit, and delete the evenement
//            Button reserveBtn = new Button("Add");
//            reserveBtn.addActionListener(e -> {
//          //  Reserve the evenement
//           });
//            Button deleteBtn = new Button("Delete");
//            deleteBtn.addActionListener(a -> {
//                //evenementService.getInstance().deleteEvenement(evenement.getId_evenement());
//
//                evenementContainer.removeComponent(evenementRow);
//            });
            // add(remove);
//evenementContainer.removeComponent(evenementRow);

Button remove = new Button("remove");
                System.out.println(local.getNum());
            remove.addActionListener(e -> {

              LocalService.getInstance().deleteLocal(local.getNum());
                 LocalListForm a = new LocalListForm(this);
                a.show();
                
                evenementContainer.removeComponent(evenementRow);
            });
            //evenementcontainer.removeAll()
         
            Button modifier = new Button("modifier");
            //modifier.addActionListener(e -> {
              //   modifierLocal h = new modifierEvenement(res,current,p);
                //h.show();
             modifier.addActionListener(e-> new UpdateLocalForm(this, local.getNum()).show());
                
                
          //  });
Button pdf=new Button("PDF");
    pdf.addActionListener((ActionEvent m) -> {
    try {
        Document document = new Document() {};
        String outputPath = "file:///C:/xampp/htdocs/ImageP/offre" + local.getNum() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        document.open();

        document.add(new Paragraph("Fiche des Locaux "));
        document.add(new Paragraph("Description :" + local.getDescript()));
        document.add(new Paragraph("Lieu :" + local.getLieu()));
         document.add(new Paragraph("surface :" +local.getSurface()));
          document.add(new Paragraph("Capacite :" + local.getNbper()));
        

        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});


            // Add the labels to the evenement row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(num_localLabel);
            labelsContainer.add(descript_localLabel);
            labelsContainer.add(lieu_localLabel);
            labelsContainer.add(surface_localLabel);
            labelsContainer.add(nbpers_localLabel);
             labelsContainer.add(prix_localLabel);
              labelsContainer.add(equipements_localLabel);
               labelsContainer.add(disponibilite_localLabel);
                labelsContainer.add(date_localLabel);
            labelsContainer.add(categorie_idLabel);
            
            labelsContainer.add(separator);
            

            /*labelsContainer.add(description_evenementLabel);
            labelsContainer.add(utilisateur_idLabel);
            labelsContainer.add(imageLabel);
            labelsContainer.add(deleteBtn);*/
            evenementRow.add(BorderLayout.CENTER, labelsContainer);

//           //  Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("evenementButtonBox");

            
            //    add(modifier);

            buttonsContainer.add(remove);
            buttonsContainer.add(modifier);
            buttonsContainer.add(pdf);
//           // buttonsContainer.add(deleteBtn);
//         
            evenementRow.add(BorderLayout.EAST, buttonsContainer);

            // Add the evenement row to the container
            evenementContainer.add(evenementRow);
               evenementContainer.revalidate();
evenementContainer.repaint();

        }
           
           
           
           
           
    
        });
        
        
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        add(new Label("______________________________________________________________________________________________________________________"));

       // add(ajout);
          add(ajout1);
        add(new Label("______________________________________________________________________________________________________________________"));

        add(ajout);
        add(new Label("______________________________________________________________________________________________________________________"));

        add(tri);
         add(tri2);
       add(stat);
        add(new Label("______________________________________________________________________________________________________________________"));

        // Create a container to hold the evenements
      //  Container evenementContainer = new Container();
        //evenementContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//        evenementContainer.setScrollableY(true);        
        // Add each evenement to the container with buttons to reserve, edit, and delete it
//           Button reserveBtn = new Button("Add");
//            reserveBtn.addActionListener(e -> {
        //  Reserve the evenement
//           });
        for (Local local : locaux) {

            // Create a container to hold the evenement's information and buttons
            Container evenementRow = new Container(new BorderLayout());
            evenementRow.setUIID("UserBox");

            // Create labels to display the evenement's information
            Label num_localLabel = new Label("id_local: " + local.getNum());
            num_localLabel.setUIID("num_localLabel");

            Label descript_localLabel = new Label("descript_local: " + local.getDescript());
            descript_localLabel.setUIID("descript_localLabel");

            Label lieu_localLabel = new Label("lieu_local: " + local.getLieu());
            lieu_localLabel.setUIID("lieu_localLabel");

             Label date_localLabel = new Label("date_local: " + local.getDate());
            date_localLabel.setUIID("date_localLabel");
            Label surface_localLabel = new Label("surface_local: " + local.getSurface());
            surface_localLabel.setUIID("surface_localLabel");

            Label nbpers_localLabel = new Label("nbpers_local: " + local.getNbper());
            nbpers_localLabel.setUIID("nbpers_localLabel");
            
             Label prix_localLabel = new Label("prix_local: " + local.getPrix());
            prix_localLabel.setUIID("prix_localLabel");
             Label equipements_localLabel = new Label("equipements_local: " + local.getEquipements());
            equipements_localLabel.setUIID("lieu_localLabel");
            Label disponibilite_localLabel = new Label("disponibilte_local: " + local.getDisponibilite());
            disponibilite_localLabel.setUIID("disponibilite_localLabel");
            
            

            Label categorie_idLabel = new Label("categorie_id: " + local.getCodec());
            categorie_idLabel.setUIID("utilisateur_idLabel");

            Label separator = new Label("******");
            categorie_idLabel.setUIID("separator");

            /* Label imageLabel = new Label("image: " + evenement.getPrix_evenement());
            imageLabel.setUIID("imageLabel");*/
            System.out.println("test");

            // Create buttons to reserve, edit, and delete the evenement
//            Button reserveBtn = new Button("Add");
//            reserveBtn.addActionListener(e -> {
//          //  Reserve the evenement
//           });
//            Button deleteBtn = new Button("Delete");
//            deleteBtn.addActionListener(a -> {
//                //evenementService.getInstance().deleteEvenement(evenement.getId_evenement());
//
//                evenementContainer.removeComponent(evenementRow);
//            });
            // add(remove);
//evenementContainer.removeComponent(evenementRow);

Button remove = new Button("remove");
                System.out.println(local.getNum());
            remove.addActionListener(e -> {

              LocalService.getInstance().deleteLocal(local.getNum());
                 LocalListForm a = new LocalListForm(this);
                a.show();
                
                evenementContainer.removeComponent(evenementRow);
            });
            //evenementcontainer.removeAll()
         
            Button modifier = new Button("modifier");
            //modifier.addActionListener(e -> {
              //   modifierLocal h = new modifierEvenement(res,current,p);
                //h.show();
             modifier.addActionListener(e-> new UpdateLocalForm(this, local.getNum()).show());
                
             Button pdf=new Button("PDF");
    pdf.addActionListener((ActionEvent m) -> {
    try {
        Document document = new Document() {};
        String outputPath = "file:///C:/xampp/htdocs/ImageP/offre" + local.getNum() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        document.open();

        document.add(new Paragraph("Fiche des Locaux "));
        document.add(new Paragraph("Description :" + local.getDescript()));
        document.add(new Paragraph("Lieu :" + local.getLieu()));
         document.add(new Paragraph("surface :" +local.getSurface()));
          document.add(new Paragraph("Capacite :" + local.getNbper()));
        

        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
   
          //  });



            // Add the labels to the evenement row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(num_localLabel);
            labelsContainer.add(descript_localLabel);
            labelsContainer.add(lieu_localLabel);
            labelsContainer.add(surface_localLabel);
            labelsContainer.add(nbpers_localLabel);
             labelsContainer.add(prix_localLabel);
              labelsContainer.add(equipements_localLabel);
               labelsContainer.add(disponibilite_localLabel);
                labelsContainer.add(date_localLabel);
            labelsContainer.add(categorie_idLabel);
            
            labelsContainer.add(separator);
            

            /*labelsContainer.add(description_evenementLabel);
            labelsContainer.add(utilisateur_idLabel);
            labelsContainer.add(imageLabel);
            labelsContainer.add(deleteBtn);*/
            evenementRow.add(BorderLayout.CENTER, labelsContainer);

//           //  Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("evenementButtonBox");

            
            //    add(modifier);

            buttonsContainer.add(remove);
            
            buttonsContainer.add(modifier);
             buttonsContainer.add(pdf);
//           // buttonsContainer.add(deleteBtn);
//         
            evenementRow.add(BorderLayout.EAST, buttonsContainer);

            // Add the evenement row to the container
            evenementContainer.add(evenementRow);

        }

        // Add the container to the form
        add(evenementContainer);

    }

    public void showEvenementList() {
        LocalListForm form = new LocalListForm(this);
        form.show();

    }

}
