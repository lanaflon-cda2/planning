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
    
    Connection con;
    Statement state;
    ResultSet res;
    
    private final int numEns;
    private final int numGroupe;
    private final int numMatiere;
    private final int numFiliere;
    private final int numCreneau;
    private Date dateSysteme;
    private Date dateFin;
    
    ArrayList creneauxMatchEnsGroupe = null;
    ArrayList permutPossible = null;
    
    public Absence(Seance s) {
        
        updateEtat(s, 0);
        
        this.numEns = s.getNumEns();
        this.numGroupe = s.getNumGroupe();
        this.numMatiere = s.getNumMatiere();
        this.numFiliere = s.getNumFiliere();
        this.numCreneau = s.getnumCreneau();
        
        searchRattrapage();
        
    }
    
    public ArrayList getCreneauxMatchEnsGroupe(){
        return this.creneauxMatchEnsGroupe;
    }
    
    public ArrayList getPermutPossible() {
        return this.permutPossible;
    }
    
    private void searchRattrapage() {
        
        con = ConnexionBD.init();
       
        this.dateSysteme = new Date(Calendar.getInstance().getTime().getTime());

        dateFin = getDateFin();
        
        creneauxMatchEnsGroupe = searchMatchEnsGroupe();
        
        if(creneauxMatchEnsGroupe == null){
            permutPossible = searchPermut();
        }
        try {
            res.close();
            state.close();
            con.close();
        } catch(SQLException e){
            System.out.println("SQLException: "+e);
        }
        
    }
            
    public static boolean updateEtat(Seance s,int etat){
       
        s.setEtatSeance(etat);
        SeanceDAO seanceDAO = new SeanceDAO(ConnexionBD.init());
        seanceDAO.update(s);
        return true;   
    }

    public Date getDateFin(){
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "SELECT DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe+" and NumMatiere="+numMatiere;
            query += " and NumFiliere = " + numFiliere;
            res = state.executeQuery(query);
            while(res.next()) {
                dateFin = res.getDate(1);                 
            } 
        } catch (SQLException e) {
             //e.printStackTrace();
        } 
        return dateFin;
    }
    
    
    public ArrayList getCreneauxVideEns(){
            ArrayList creneauxVideEns = null;
        try {
            creneauxVideEns = new ArrayList();
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "Select NumCreneau From Creneau where DateCreneau >= " + dateSysteme + " and DateCreneau <= " + dateFin;
            query += (" EXCEPT Select NumCreneau from Seance where NumEns = " + numEns);
            res = state.executeQuery(query); 
            while(res.next()){
                creneauxVideEns.add(res.getInt(1));
            }
      
        } catch (SQLException e) {
             //e.printStackTrace();
        }
      return creneauxVideEns;     
    }
    
    public ArrayList getCreneauxVideGroupe(){
        ArrayList creneauxVideGroupe = null;
        try {
            creneauxVideGroupe = new ArrayList();
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String();
            query += "Select numCreneau From Creneau where DateCreneau >= "+ dateSysteme +" and DateCreneau <= "+ dateFin;
            query += (" EXCEPT Select NumCreneau from Seance where NumGroupe = " + numGroupe + " and NumFiliere = " + numFiliere + " and EtatSeance=1");
            res = state.executeQuery(query);
            while(res.next()){
                creneauxVideGroupe.add(res.getInt(1));
            }
      
        } catch (SQLException e) {
             //e.printStackTrace();
        }
      return creneauxVideGroupe;     
    
    }
    
    public ArrayList searchMatchEnsGroupe () {
        
        ArrayList creneauxMatch = null;
        ArrayList creneauxVideEns = getCreneauxVideEns();
       
        ArrayList creneauxVideGroupe = getCreneauxVideGroupe();
        for(int i = 0; i < creneauxVideEns.size(); i++){
            int num = (int) creneauxVideEns.get(i);
            for(int j=0 ; j < creneauxVideGroupe.size(); j++){
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
    
    public ArrayList getPossibleEnsXPermut(){
        
        ArrayList possibleEnsPermut = null;
        try {
            possibleEnsPermut = new ArrayList();
            String query = new String();
            query += "SELECT DISTINCT numEns FROM Seance WHERE NumEns != " + numEns;
            query += "NumGroupe = " + numGroupe+"and NumFiliere = " + numFiliere;
            query += (" EXCEPT Select numEns from Seance where numCreneau="+numCreneau+" and numGroupe="+numGroupe+"and NumFiliere = " + numFiliere);
            res = state.executeQuery(query);
            while(res.next()) {
                    possibleEnsPermut.add(res.getInt(1));
                             
            }
            
        } catch (SQLException e) {
             //e.printStackTrace();
        }
        return possibleEnsPermut;
    }
    
    public ArrayList getSeancesDeEnsX(){
        ArrayList seance = null;
        
        try {
            seance = new ArrayList();
            String query= new String();
            query += "Select NumCreneau from Seance where NumEns="+numEns+" and NumGroupe"+numGroupe+"and NumFiliere = " + numFiliere;
            query += (" INTERSECT Select NumCreneau From Creneau where DateCreneau>="+dateSysteme+"and DateCreneau<="+dateFin);
            res=state.executeQuery(query);
            while(res.next()){
                seance.add(res.getInt(1));
            }
                
        } catch (SQLException e) {
             //e.printStackTrace();
        }
        return seance; 
    }
    
    public ArrayList searchPermut(){
        ArrayList creneauxMatchList = null;
        CreneauPermut creneauxMatchWithX;
        ArrayList seanceDeEnsX;
        ArrayList ensList = getPossibleEnsXPermut();
        ArrayList creneauxVideEns = getCreneauxVideEns();
        for (Object ensX : ensList) {
            seanceDeEnsX = getSeancesDeEnsX();
            creneauxMatchWithX = null;
            
            
            for(Object seance: seanceDeEnsX){
                for(Object creneauVide: creneauxVideEns){
                    if(creneauVide == seance) {
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
    
