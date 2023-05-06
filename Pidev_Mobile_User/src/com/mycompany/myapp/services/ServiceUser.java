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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class ServiceUser {
    
 
    
    
    
    public User user;


    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
      public ArrayList<User> Users;

    private ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
    
    public ArrayList<User> parseUsers(String jsonText) {
        try {
           Users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User t = new User();
               if (obj.get("idUser") == null) {
    t.setId(0);
} else {
    float id = Float.parseFloat(obj.get("idUser").toString());
    t.setId((int) id);
}
                
                if (obj.get("nom")==null){
                    
                    t.setNom("error");
                }
                
                else{
                    
                t.setNom((obj.get("nom").toString()));
                }
                
                if (obj.get("prenom")==null){
                    
                    t.setPrenom("error");
                }
                
                else{
                
                t.setPrenom((obj.get("prenom").toString()));
                }
                if (obj.get("email")==null){
                    
                    t.setPrenom("error");
                }
                
                else{
                
                t.setEmail((obj.get("email").toString()));
                }
 
                
                if (obj.get("mdp")==null){
                    
                    t.setPrenom("error");
                }
                
                else{
                
                t.setMdp((obj.get("mdp").toString()));
                }
 
                if (obj.get("role")==null){
                    
                    t.setPrenom("error");
                }
                
                else{
                
                t.setRole((obj.get("role").toString()));
                }
                
                
                if (obj.get("image")==null){
                    
                    t.setPrenom("error");
                }
                
                else{
                
                t.setImage((obj.get("image").toString()));
                }
                
                if (obj.get("tel") == null) {
    t.setTel(0);
} else {
    float tel = Float.parseFloat(obj.get("tel").toString());
    t.setTel((int)tel);
}
                
 
 
                
                
                
                
                
                
                Users.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Users;
    }
    
    
    
  
    
    
    
     public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    
     
     
     public boolean addUser(User u){
    String nom = u.getNom();
    String prenom = u.getPrenom();
    String email = u.getEmail();
    String mdp = u.getMdp();
    int tel = u.getTel();
    String role = u.getRole();
    
    String url = Statics.ADD_USER_URL + "nom=" + nom + "&prenom=" + prenom + "&email=" + email + "&role=" + role + "&tel=" + tel + "&mdp=" + mdp;
    
    ConnectionRequest req = new ConnectionRequest(); // Initialize req here
    
    req.setUrl(url);
    req.setPost(true);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
     
     
     
     
     
     
     
     
     
    
    
}
