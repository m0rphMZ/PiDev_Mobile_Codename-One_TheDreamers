/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

/**
 *
 * @author bhk
 */
public class Statics {
    //public static final String BASE_URL="https://workshopparsingjsoncn1back-production.up.railway.app/task/";
    public static final String ADDREC_URL="http://127.0.0.1:8000/reclamationJSON";
    public static final String RECBYUSERID_URL="http://127.0.0.1:8000/reclamationsParUserIdJSON";
//    public static final String RECBYRECID_URL="http://127.0.0.1:8000/reclamationParRecIdJSON";
//    public static final String FETSHREPS_URL="http://127.0.0.1:8000/reponsesParRecIdJSON";
    
    public static final String NEWREP_URL="http://127.0.0.1:8000/reclamationNewRepJSON";
    
    public static final String NEWREPADMIN_URL="http://127.0.0.1:8000/reclamationNewRepAdminJSON";
    
    public static final String REMOVEREC_URL="http://127.0.0.1:8000/deleteRecJSON";

    
    
    public static final String RECPLUSREPBYRECID_URL="http://127.0.0.1:8000/reclamationEtReponsesParRecIdJSON";
    
    public static final String RECSADMIN_URL="http://127.0.0.1:8000/reclamationsAdminJSON";
    
    
    
//    ________________________________________USER____________________________________________
    
        public static final String USERS_URL="http://127.0.0.1:8000/ListUser";
       public static final String ADD_USER_URL="http://127.0.0.1:8000/newUserJson?";
       public static final String Delete_User="http://127.0.0.1:8000/DeleteUserJSON/";
       public static final String Update_User="http://127.0.0.1:8000/UpdateUserJSON/";
       public static final String Show_User ="http://127.0.0.1:8000/UserJSON/";
    
    //    ________________________________________EVENT____________________________________________
       //Aymen:
       public static final String EVENT_BASE_URL="http://localhost:8000/eventlist";
       public static final String ADD_EVENT_BASE_URL="http://localhost:8000/eventnew";
       public static final String SHOW_EVENT_BASE_URL="http://localhost:8000/eventshow/";
       public static final String UPDATE_EVENT_BASE_URL="http://localhost:8000/eventupdate/";
       public static final String DELETE_EVENT_BASE_URL="http://localhost:8000/eventdelete/";
    
       
       /////////////////////////////////////produit/////////////////////////////////////////
         public static final String BASE_URL="http://127.0.0.1:8000";//hathi url mt3 symfony 
}
