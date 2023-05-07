/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.HiddenTunisia.services;

/**
 *
 * @author Administrateur
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.HiddenTunisia.entities.Local;
import tn.esprit.HiddenTunisia.utils.statics;
public class LocalService {
     
    private ConnectionRequest req;
    private boolean resultOK;
    private static LocalService instance = null;
    private Resources res;
    private ArrayList<Local> locaux;
    
    private LocalService() {
        req = new ConnectionRequest();
    }
    
    public static LocalService getInstance() {
        if (instance == null) {
            instance = new LocalService();
        }
        return instance;
    }
    
    public ArrayList<Local> parseEvenement(String jsonText) {
        try {
            locaux = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                Local u = new Local();
                
                
                
             float id =  Float.parseFloat(obj.get("num_loc").toString());
            int idAsInt = (int) id;
            u.setNum((int) idAsInt);
                
                String descript_local = obj.get("descipt_loc").toString();
                u.setDescript((descript_local));
                
                String lieu_local = obj.get("lieu_loc").toString();
                u.setLieu(lieu_local);
                
          String date_loc = obj.get("date_Aloc").toString();
                u.setDate(date_loc);
                
               /* String lieux_evenement = obj.get("lieux_evenement").toString();
                u.setLieux_evenement(lieux_evenement);*/
                
                float surface_local = Float.parseFloat(obj.get("surface_loc").toString());
                u.setSurface((Float) surface_local);
                
                
                 float id1 =  Float.parseFloat(obj.get("nb_pers_loc").toString());
            int nbpers = (int) id1;
            u.setNbper((int) nbpers);
                
                        
                
               /* String image = obj.get("image").toString();
                u.setImage(image);*/
                
                  float categorie_id =  Float.parseFloat(obj.get("code_catg").toString());
            int categorir_idAsInt = (int) categorie_id;
            u.setCodec((int) categorir_idAsInt);
                
                
                
//            'prix_loc' => $location->getPrixLoc(),
//           'equipements' => $location->getEquipements(),
//            'disponibilite' => $location->getDisponibilite(),    
//                
            
            float prix_local = Float.parseFloat(obj.get("prix_loc").toString());
                u.setPrix((Float) prix_local);
            
              String equipements = obj.get("equipements").toString();
                u.setEquipements((equipements));
                 String disponibilite = obj.get("disponibilite").toString();
                u.setDisponibilite((disponibilite));
                
//                v.setCouleur(obj.get("couleur").toString());
//                v.setEtat(obj.get("etat").toString());

                //Ajouter le vélo extrait de la réponse Json à la liste
                locaux.add(u);
            }

        } catch (IOException ex) {

        }
        
        return locaux;
    }

    public ArrayList<Local> getAllLocaux() {
        ArrayList<Local> listEvenement= new ArrayList<>();
        int id = 2;
        String url = statics.BASE_URL + "/afficherJsonLocal";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locaux = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locaux;
    }
    
    
    public boolean addLocal(Local e) {
       // String date_evenement = e.getDate_evenement().toString().substring(0, 10);
       /*$local->setDesciptLoc($request->get('descipt_loc'));
        $local->setLieuLoc($request->get('lieu_loc'));
        $local->setDateAloc(new \DateTime($request->get('date_Aloc')));
        $local->setSurfaceLoc($request->get('surface_loc'));
        $local->setPrixLoc($request->get('prix_loc'));
        $local->setEquipements($request->get('equipements'));
        $local->setDisponibilite($request->get('disponibilite'));
        $local->setNbPersLoc($request->get('nb_pers_loc'));
        $local->setImage($request->get('image'));*/
       ///api/ajoutLocal?descipt_loc=888&lieu_loc=ggg&surface_loc=7.5&prix_loc=8&equipements=gggg&disponibilite=tt&nb_pers_loc=77&image=77&code_catg=0
                String url = statics.BASE_URL + "/api/ajoutLocal?descipt_loc=" + e.getDescript()+ "&lieu_loc=" + e.getLieu()+ "&surface_loc=" + e.getSurface()+  "&prix_loc="+e.getPrix()+  "&equipements="+e.getPrix()+"&disponibilite="+e.getDisponibilite()+"&nb_pers_loc=" +8 +"&image=" + e.getImage()+ "&code_catg=" + 777 ; //création de l'URL
 //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//        
//        
//        
//          req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
        
        
        
        
    }
    
    
   public void deleteLocal(int id) {

        Dialog d = new Dialog();
        if (d.show("Delete Local"
                + "..", "Do you really want to remove this Local", "Yes", "No")) {

            req.setUrl(statics.BASE_URL + "/api/delete/local/" + id);
       
            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
        }
}
