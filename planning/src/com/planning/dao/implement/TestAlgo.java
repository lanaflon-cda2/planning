/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.controler.Absenter;
import com.planning.controler.Permut;
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
public class TestAlgo {
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
        Enseignant enseignant = new Enseignant(3, "Keita", "lancine", "keita@gmail.com", "658749", user.getIDUser());
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
    
    public static String getDateName(Date d){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //System.out.print("Today  is");
        switch (day) {
            case 1:
                return "Dimanche";
            case 2:
                return "Lundi";
            case 3:
                return "Mardi";
            case 4:
                return "Mercredi";
            case 5:
                return "Jeudi";
            case 6:
                return "Vendredi";
            default:
                return "Samedi";
        }
    }
    
    
    public static void insertAllCreneau(){
        CreneauDAO x = new CreneauDAO(conn);
        
        x.insererAllCreneau(2016, 2017);
    }
    
    public static void insertSeance(){
        
        SeanceDAO s = new SeanceDAO(conn);
        Calendar cal = Calendar.getInstance();
        Calendar a = Calendar.getInstance();
        a.set(2016, 8, 26);
        Calendar b = Calendar.getInstance();
        b.set(2016, 10, 11);
        Calendar c = Calendar.getInstance();
        c.set(2016, 10, 21);
        Calendar d = Calendar.getInstance();
        d.set(2017, 0, 6);
        
        cal.set(0, 0, 0, 14, 0, 0);
        long timeMillis = cal.getTimeInMillis();
        Time t = new Time(timeMillis);
        cal = c;
        timeMillis = cal.getTimeInMillis();
        Date dd = new Date(timeMillis);
        cal = d;
        timeMillis = cal.getTimeInMillis();
        Date df = new Date(timeMillis);
 
        StatiqueCreneau so = new StatiqueCreneau(14, 1, 18, 3, t, dd, df);
        s.insertAllSeanceForStatiqueCreneau(so);
    }
    
    
    public static void testAbsence(){
        CreneauDAO creneauDAO = new CreneauDAO(conn);
        Creneau obj;
        Seance s = new Seance(52, 274, 6, 15, 1);
        
        Absenter absence = new Absenter(s);
        ArrayList creno = absence.getCreneauxMatchEnsGroupe();
        if(creno != null) {
            System.out.println("Les creneaux trouvés sont: ");
            for(int i = 0; i < creno.size(); i++) {
                obj = creneauDAO.find((int) creno.get(i));
                System.out.println("numCreneau " + obj.getNumCreneau() + " " + TestAlgo.getDateName(obj.getDateCreneau())+ " " + obj.getDateCreneau() + " " + obj.getHeureCreneau());
            }
            
        } else System.out.println(" Creno = " + creno + " Aucun creno vide trouvée.");
        
        //Supposons que creno = null. On va chercher si des permutations sont possibles; 
        System.out.println("\n\n");
        
        ArrayList permut = absence.getPermutPossible();
        
        if(permut != null) {
            Permut x;
             
            for(int j = 0; j < permut.size(); j++) {

                x = (Permut) permut.get(j);
                int numEnsX = x.getNumEns();
                System.out.println("Le professeur de numero " + numEnsX + " peut offrir les créneaux suivants: ");
                ArrayList listCreneauEnsX = x.getCreneaux();
                for(int k = 0; k < listCreneauEnsX.size(); k++){
                    obj = creneauDAO.find((int) listCreneauEnsX.get(k));
                    System.out.println("numCreneau " + obj.getNumCreneau() + " " + TestAlgo.getDateName(obj.getDateCreneau())+ " " + obj.getDateCreneau() + " " + obj.getHeureCreneau());
                }

            }
        
        } 
    }
    
    public static void main(String args[]){
        
        //TestDAO.insertSeance();
        TestAlgo.testAbsence();
    }

}
