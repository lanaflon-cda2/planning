 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;

import com.planning.dao.implement.SeanceDAO;
import com.planning.model.ConnexionBD;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oumaima
 */
public class Absence {
    
    static Connection con = ConnexionBD.init();
    Statement state;
    ResultSet res;
    
    private final int numEns;
    private final int numGroupe;
    private final int numMatiere;
    private final int numCreneau;
    private Date dateSysteme;
    private Date dateFin;
    
    ArrayList creneauxMatchEnsGroupe = null;
    ArrayList permutPossible = null;
    
    public Absence(Seance s) {
        
        Absence.updateEtat(s, 0);
        
        this.numEns = s.getNumEns();
        this.numGroupe = s.getNumGroupe();
        this.numMatiere = s.getNumMatiere();
        this.numCreneau = s.getNumCreneau();
        this.dateSysteme = new Date(Calendar.getInstance().getTime().getTime());
        
        this.searchRattrapage();
        
    }
    
    public ArrayList getCreneauxMatchEnsGroupe(){
        return this.creneauxMatchEnsGroupe;
    }
    
    public ArrayList getPermutPossible() {
        return this.permutPossible;
    }
    
    private void searchRattrapage() {
        System.out.println("La date systeme = " + dateSysteme);
        this.dateFin = this.getDateFin();
        System.out.println("Date de fin de la fin matiere = " + dateFin);
        creneauxMatchEnsGroupe = this.searchMatchEnsGroupe();
        permutPossible = this.searchPermut();

        if(creneauxMatchEnsGroupe == null){
            System.out.println("Aucune intersection trouvée.");
            if(permutPossible == null) {
                System.out.println(" Et aucune permutation possible");
            }
            else {
                System.out.println("Mais des permutations sont possibles");
            }
        }
        else {
             if(permutPossible == null) {
                System.out.println(" Intersection trouvee mais aucune permutation trouvee");
             } 
             else {
                System.out.println("Intersection trouvee et des permutations sont aussi possibles");
            }
        }
        
        try {
            res.close();
            state.close();
            con.close();
        } catch(SQLException e){
            System.out.println("SQLException: " + e);
        }
        
    }
            
    public static boolean updateEtat(Seance s,int etat){
       
        s.setEtatSeance(etat);
        SeanceDAO seanceDAO = new SeanceDAO(con);
        seanceDAO.update(s);
        return true;   
    }

    private Date getDateFin(){
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "SELECT DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe + " and NumMatiere= "+ numMatiere;
            res = state.executeQuery(query);
            while(res.next()) {
                dateFin = res.getDate(1);                 
            } 
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        } 
        return dateFin;
    }
    
    
    private ArrayList getCreneauxVideEns(){
            ArrayList creneauxVideEns = null;
        try {
            creneauxVideEns = new ArrayList();
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "Select NumCreneau From Creneau where Date >= '" + dateSysteme + "' and Date <= '" + dateFin;
            query += "' AND NumCreneau NOT IN (Select NumCreneau from Seance where NumEns = " + numEns + ")";
            res = state.executeQuery(query); 
            while(res.next()){
                creneauxVideEns.add(res.getInt(1));
            }
      
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
      return creneauxVideEns;     
    }
    
    private ArrayList getCreneauxVideGroupe(){
        ArrayList creneauxVideGroupe = null;
        try {
            creneauxVideGroupe = new ArrayList();
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "Select NumCreneau From Creneau where Date >= '"+ dateSysteme + "' and Date <= '" + dateFin + "' and NumCreneau != " + numCreneau;
            query += " AND NumCreneau NOT IN (Select NumCreneau from Seance where NumGroupe = " + numGroupe + " and EtatSeance = 1" + ")";
            res = state.executeQuery(query);
            while(res.next()){
                creneauxVideGroupe.add(res.getInt(1));
            }
      
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
      return creneauxVideGroupe;     
    
    }
    
    private ArrayList searchMatchEnsGroupe () {
        
        ArrayList creneauxMatch = null;
        ArrayList creneauxVideEns = this.getCreneauxVideEns();
       
        ArrayList creneauxVideGroupe = this.getCreneauxVideGroupe();
        for(int i = 0; i < creneauxVideEns.size(); i++){
            int num = (int) creneauxVideEns.get(i);
            for(int j = 0 ; j < creneauxVideGroupe.size(); j++){
                int numg = (int) creneauxVideGroupe.get(j);
                if (num == numg){
                    if(creneauxMatch == null) creneauxMatch = new ArrayList();
                    creneauxMatch.add(numg);
                    break;
                }
            }
        }
        return creneauxMatch;     
    }
    
    private ArrayList getPossibleEnsXPermut(){
        
        ArrayList possibleEnsPermut = null;
        try {
            possibleEnsPermut = new ArrayList();
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "SELECT DISTINCT NumEns FROM Seance WHERE NumEns != " + numEns;
            query += " AND NumGroupe = " + numGroupe;
            query += " AND NumEns NOT IN (Select NumEns from Seance where NumCreneau = "; 
            query += numCreneau + ")";
            res = state.executeQuery(query);
            while(res.next()) {
                    possibleEnsPermut.add(res.getInt(1));
                             
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
        return possibleEnsPermut;
    }
    
    private ArrayList getSeancesDeEnsX(int numEnsX){
        ArrayList seance = null;
        
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            seance = new ArrayList();
            String query= new String();
            query += "Select NumCreneau from Seance where NumEns = " + numEnsX + " and NumGroupe = " + numGroupe;
            query += " and NumCreneau IN (Select NumCreneau From Creneau where Date >= '" + dateSysteme + "' and Date <= '" + dateFin + "')";
            res = state.executeQuery(query);
            while(res.next()){
                seance.add(res.getInt(1));
            }
                
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
        return seance; 
    }
    
    private ArrayList searchPermut(){
        System.out.println("search permut in methode");
        ArrayList creneauxMatchList = null;
        CreneauPermut creneauxMatchWithX;
        ArrayList seanceDeEnsX;
        ArrayList ensList = this.getPossibleEnsXPermut();
        ArrayList creneauxVideEns = this.getCreneauxVideEns();

        for (Object ensX : ensList) {
            seanceDeEnsX = this.getSeancesDeEnsX((int) ensX);
            creneauxMatchWithX = null;

            for(Object seance: seanceDeEnsX){
                for(Object creneauVide: creneauxVideEns){
                    if((int) creneauVide == (int) seance) {
                        if(creneauxMatchWithX == null) {
                            creneauxMatchWithX = new CreneauPermut();
                            creneauxMatchWithX.setNumEns((int) ensX);
                        }
                        creneauxMatchWithX.addCreneaux((int) creneauVide);
                    }   
                }
            }
            
            
            if(creneauxMatchWithX != null) {
                if(creneauxMatchList == null) creneauxMatchList = new ArrayList();
                creneauxMatchList.add(creneauxMatchWithX); 
            }           
        }
        
        
        return creneauxMatchList;
    }
    
    
    
}
    
