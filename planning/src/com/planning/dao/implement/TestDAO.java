/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.controler.Absence;
import com.planning.model.ConnexionBD;
import com.planning.model.Creneau;
import com.planning.model.Enseignant;
import com.planning.model.Seance;
import com.planning.model.StatiqueCreneau;
import com.planning.model.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author genereux
 */
public class TestDAO {
    static Connection conn = ConnexionBD.init();
    
    public static void testCreneau () {
             
        
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 1, 29, 8, 00, 00);
        long de = cal.getTimeInMillis();
        System.out.println(de);
        
        Date dd = new Date(de);
        Time t = new Time(de);
        
        System.out.println(t + " " + dd);
        
        Creneau x = new Creneau(dd, t);
        
        CreneauDAO e = new CreneauDAO(conn);
        
        if(e.create(x)) {
            System.out.println("Creneau inserer");
        }
        
        else System.out.println("Error");
        
     
        cal.set(2016, 2, 29, 8, 00, 00);
        de = cal.getTimeInMillis();
        System.out.println(de);
        
    dd = new Date(de);
        t = new Time(de);
        
        System.out.println(t + " " + dd);
        
     x = new Creneau(dd, t);
        
        if(e.create(x)) {
            System.out.println("Creneau inserer");
        }
        
        else System.out.println("Error");
        
    
    }
    
    public static void testUsers(){
    
        Users user = new Users("Oumaima", "belahsen");
        UsersDAO usersDAO = new UsersDAO(conn);
        if(usersDAO.create(user)){
            
            System.out.println("Insertion de l'utilisateur " + user.getIDUser() + "réussie.");
        }else {
            System.out.println("Echec pour user");
        }
        usersDAO = new UsersDAO(conn);
        
        if(usersDAO.find(user.getIDUser()) != null) {
            System.out.println("Utilisateur trouvé.");
            
        } else {
            System.out.println("Utilisateur non trouvé");
        }

        
        if(usersDAO.delete(user)){
            System.out.println("Suppression réussie.");
        }else {
            System.out.println("Echec");
        }
    }
       
    public static void testEnseignant(){
        Users user = new Users("Oumaima", "belahsen");
        Enseignant enseignant = new Enseignant(3, "oUMAIMA", "lancine", "keita@gmail.com", 6878349, user.getIDUser());
        EnseignantDAO enseignantDAO = new EnseignantDAO(conn);
        
        if(enseignantDAO.create(enseignant)) {
            System.out.println("Insertion Réussie de l'enseignant");
        } else {
        
            System.out.println("Echec pour enseignant");
        }
        enseignantDAO = new EnseignantDAO(conn);
        if(enseignantDAO.find(enseignant.getNumEns()) != null) {
            System.out.println("Enseignanttrouvé.");
            
        } else {
            System.out.println("Enseignant non trouvé");
        }
    
    }
    
    public static void testInsertAllCreneau(){
        CreneauDAO x = new CreneauDAO(conn);
        
        x.insererAllCreneau(2016, 2017);
    }
    
    public static void InsertSeance(){
        SeanceDAO s = new SeanceDAO(conn);
        Calendar cal = Calendar.getInstance();
        cal.set(0, 0, 0, 14, 0, 0);
        long timeMillis = cal.getTimeInMillis();
        Time t = new Time(timeMillis);
        cal.set(2016, 10, 14);
        timeMillis = cal.getTimeInMillis();
        Date dd = new Date(timeMillis);
        cal.set(2016, 11, 30);
        timeMillis = cal.getTimeInMillis();
        Date df = new Date(timeMillis);
              
        StatiqueCreneau so = new StatiqueCreneau(12, 2, 14, 6, t, dd, df);
        s.insertAllSeanceForStatiqueCreneau(so);
    }
    
    
    public static void testAbsence(){
        Seance s = new Seance(10068, 172, 9, 4, 1);
        Absence absence = new Absence(s);
        ArrayList creno = absence.getCreneauxMatchEnsGroupe();
        if(creno != null) {
            for(int i = 0; i < creno.size(); i++) {
                System.out.println("les creneaux trouvés sont : " + (int) creno.get(i));
            }
            
        } else System.out.println(" Creno = " + creno);
        
    }
    
    public static void main(String args[]){
       //InsertSeance();
       testAbsence();
       
       
    }
    
}
