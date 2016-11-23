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
import java.util.Date;

/**
 *
 * @author Oumaima
 */
public class Absence {
    Statement state ;
    ResultSet res;
    Connection con = ConnexionBD.init();
    
    public boolean updateEtat(Seance s,int etat){
        s.setEtatSeance(etat);
        SeanceDAO seanceDAO = new SeanceDAO(ConnexionBD.init());
        if (seanceDAO.update(s)){
            return true ;
        }
    return false ;    
    }

    public Date getDateFin(int numGroupe, int numMatiere, int numFiliere){
        Date dateFin= new Date();
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe+" and NumMatiere="+numMatiere);
            query += " and NumFiliere = " + numFiliere;
            res = state.executeQuery(query);
            while(res.next()) {
                dateFin = res.getDate(1);                 
            } 
        } catch (SQLException e) {
             e.printStackTrace();
        } finally{
            if(res != null){
                try{
                res.close();
                }
                catch(SQLException e){    
                }
            }
            if(state != null){
                try{
                state.close();
                }
                catch(SQLException e){    
                }
            }
        return dateFin;
    }
    
    
    public ArrayList searchMatch(int numEns, int numGroupe, int numMatiere, int numFiliere){
        ArrayList creneauVideEns = new ArrayList();
        ArrayList creneauVideGroupe = new ArrayList();
        ArrayList creneauMatch = new ArrayList();
        Date df= getDateFin(numGroupe, numMatiere, numFiliere);
        Date dateSysteme = new Date();
        
        try {
            
            String query1 = new String("Select NumCreneau From Creneau where DateCreneau>="+dateSysteme+"and DateCreneau<="+df);
            query1 += (" EXCEPT Select NumCreneau from Seance where NumEns="+numEns);
            res = state.executeQuery(query1);
            while(res.next()){
                creneauVideEns.add(res.getInt(1));
            }
            String query2 = new String("Select numCreneau From Creneau where DateCreneau >= "+ dateSysteme +" and DateCreneau <= "+ df);
            query2 += (" EXCEPT Select NumCreneau from Seance where NumGroupe="+numGroupe+"and NumFiliere = " + numFiliere+ "and EtatSeance=1");
            res = state.executeQuery(query2);
            while(res.next()){
                creneauVideGroupe.add(res.getInt(1));
            }
            for(int i=0;i<creneauVideEns.size();i++){
                int num =(int) creneauVideEns.get(i);
                for(int j=0 ; j<creneauVideGroupe.size(); j++){
                    int numg =(int)creneauVideGroupe.get(j);
                    if (num==numg){
                        creneauMatch.add(numg);
                        break;
                    }
                }
            }
      
        } catch (SQLException e) {
             e.printStackTrace();
        }
        finally{
            if(res != null){
                try{
                res.close();
                }
                catch(SQLException e){    
                }
            }
            if(state != null){
                try{
                state.close();
                }
                catch(SQLException e){    
                }
            }
        }
      return creneauMatch ;     
    }
    
    public ArrayList seanceForUniqueEns(int numEns, int numGroupe , int numMatiere, int numFiliere){
        ArrayList seance = new ArrayList();
        Date df= getDateFin(numGroupe, numMatiere, numFiliere);
        Date dateSysteme = new Date();
        
        try {
            String query1= new String("Select NumCreneau from Seance where NumEns="+numEns+" and NumGroupe"+numGroupe+"and NumFiliere = " + numFiliere);
            query1 += (" INTERSECT Select NumCreneau From Creneau where DateCreneau>="+dateSysteme+"and DateCreneau<="+df);
            res=state.executeQuery(query1);
            while(res.next()){
                seance.add(res.getInt(1));
            }
                
        } catch (SQLException e) {
             e.printStackTrace();
        }
        finally{
            if(res != null){
                try{
                res.close();
                }
                catch(SQLException e){    
                }
            }
            if(state != null){
                try{
                state.close();
                }
                catch(SQLException e){    
                }
            }
        }
        return seance; 
    }
    
    public ArrayList searchPermut(int numEns , int numCreneau , int numGroupe, int numMatiere, int numFiliere){
        ArrayList allEnsSeanceList = new ArrayList();
        ArrayList ensSeanceMatchList = new ArrayList();
        EnsSeance ensSeance;
        int numEnsX;
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT numEns FROM Seance WHERE NumEns != " + numEns);
            query += "NumGroupe = " + numGroupe+"and NumFiliere = " + numFiliere;
            query += (" EXCEPT Select numEns from Seance where numCreneau="+numCreneau+" and numGroupe="+numGroupe+"and NumFiliere = " + numFiliere);
            res = state.executeQuery(query);
            while(res.next()) {
                numEnsX = res.getInt(1);
                ArrayList seanceEnsList = seanceForUniqueEns(numEns, numGroupe, numMatiere, numFiliere);
                ArrayList seanceMatch;
                             
            }
            for(int i=0 ; i<allEnsSeanceList.size();i++){
                numEns = allEnsSeanceList.get(i).getNumEns();
                ArrayList ensSeance;
                for(int j = 0; j<=)

                
            }
            
            
            
            
  
            
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        finally{
            if(res != null){
                try{
                res.close();
                }
                catch(SQLException e){    
                }
            }
            if(state != null){
                try{
                state.close();
                }
                catch(SQLException e){    
                }
            }
        }   
    }
    
    
    
}
    
