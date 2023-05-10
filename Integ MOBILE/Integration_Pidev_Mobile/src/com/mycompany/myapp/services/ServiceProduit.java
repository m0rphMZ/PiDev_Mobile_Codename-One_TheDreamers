/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit1;

//import static com.mycompany.myapp.services.ServiceTask.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ashre
 */
public class ServiceProduit {
    private static ServiceProduit instance;
    private ConnectionRequest req;
    
    private ServiceProduit() {
        req = new ConnectionRequest();
    }
    
    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
        
    public boolean addEvenement(Produit1  e) {
        String url = Statics.BASE_URL +"/produit/addProductJSON/?" + "Codeproduit=" + e.getCode()+ "&description=" +  e.getDes() +"&quantite=" + e.getPrix_produit() ;
       
        req.setUrl(url); 
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Produit1> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Produit1> evenements = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Produit1 a = new Produit1();
      
            int idf = (int) Float.parseFloat(obj.get("id").toString());
            
           int idf1= (int) Float.parseFloat(obj.get("quantite").toString());
            a.setId_produit(idf);
            a.setCode(obj.get("codeProduit").toString());

            a.setDes(obj.get("description").toString());
            a.setPrix_produit(idf1);
            
            evenements.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(evenements);
    return evenements;
}
public ArrayList<Produit1> getAllEvenement() {
    String url = Statics.BASE_URL +"/produit/showProductJSON";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parseAnnonces(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deleteEvenement(int idf) {
    String url = Statics.BASE_URL +  "/produit/deleteProductJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierEvenement(Produit1 e){
//     "/annonces/updateannoncesJSON/" + l.getIds() + "?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
String url = Statics.BASE_URL + "/produit/updateProductJSON/" + e.getId_produit()+ "?Codeproduit=" + e.getCode()+ "&description=" + e.getDes();
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}