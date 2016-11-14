/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.model.ConnexionBD;
import com.planning.model.Enseignant;
import com.planning.model.Users;
import java.sql.Connection;

/**
 *
 * @author genereux
 */
public class TestDAO {
    
    public static void main(String args[]){
        

        Users user = new Users("keita", "koria");
        UsersDAO usersDAO = new UsersDAO(ConnexionBD.init());
        if(usersDAO.create(user)){
            
            System.out.println("Insertion de l'utilisateur " + user.getIDUser() + "réussie.");
        }else {
            System.out.println("Echec pour user");
        }
        usersDAO = new UsersDAO(ConnexionBD.init());
        if(usersDAO.finds(user.getIDUser()) != null) {
            System.out.println("Utilisateur trouvé.");
            
        } else {
            System.out.println("Utilisateur non trouvé");
        }
        
        
        
        
        /*if(usersDAO.delete(user)){
            
            System.out.println("Suppression réussie.");
        }else {
            System.out.println("Echec");
        }*/
        
        Enseignant enseignant = new Enseignant(3, "keita", "lancine", "keita@gmail.com", 6878349, user.getIDUser());
        EnseignantDAO enseignantDAO = new EnseignantDAO(ConnexionBD.init());
        
        if(enseignantDAO.create(enseignant)) {
            System.out.println("Insertion Réussie de l'enseignant");
        } else {
        
            System.out.println("Echec pour enseignant");
        }
        enseignantDAO = new EnseignantDAO(ConnexionBD.init());
        if(enseignantDAO.find(enseignant.getNumEns()) != null) {
            System.out.println("Enseignanttrouvé.");
            
        } else {
            System.out.println("Enseignant non trouvé");
        }
        
        
        
        
        

        
        
    }
    
}
