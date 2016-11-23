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
    
    
    public ArrayList searchMatch(int numEns , int numGroupe , int numMatiere){
        ArrayList creneauVideEns = new ArrayList();
        ArrayList creneauVideGroupe = new ArrayList();
        ArrayList creneauMatch = new ArrayList();
 
        
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe+" and numMatiere="+numMatiere);
            res = state.executeQuery(query);
            Date df= new Date();
            Date dd= new Date();
            while(res.next()) {
                df = res.getDate(1);                 
            }
            String query1 = new String("Select numCreneau From Creneau where dateCreneau>="+dd+"and dateCreneau<="+df);
            query1 += (" EXCEPT Select numCreneau from Seance where numEns="+numEns);
            res = state.executeQuery(query1);
            while(res.next()){
                creneauVideEns.add(res.getInt(1));
            }
            String query2 = new String("Select numCreneau From Creneau where dateCreneau>="+dd+"and dateCreneau<="+df);
            query2 += (" EXCEPT Select numCreneau from Seance where numGroupe="+numGroupe+"and etatSeance=1");
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
    
    public ArrayList seanceEns(int numEns, int numGroupe , int numMatiere){
        ArrayList seance = new ArrayList();
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe+" and numMatiere="+numMatiere);
            res = state.executeQuery(query);
            Date df= new Date();
            Date dd= new Date();
            while(res.next()) {
                df = res.getDate(1);                 
            }
            
            String query1= new String("Select numCreneau from Seance where numEns="+numEns+" and numGroupe"+numGroupe);
            query1 += (" INTERSECT Select numCreneau From Creneau where dateCreneau>="+dd+"and dateCreneau<="+df);
            res=state.executeQuery(query1);
            while(res.next()){
                seance.add(res.getInt(1));
            }
                
            }
        
        
        
         catch (SQLException e) {
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
    
    public ArrayList searchPermut(int numEns , int numCreneau , int numGroupe){
        ArrayList enseignantList = new ArrayList();
        ArrayList ensSeance =new ArrayList();
        try {
            state = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT numEns FROM Enseignant WHERE NumEns != " + numEns);
            query += (" EXCEPT Select numEns from Seance where numCreneau="+numCreneau+" and numGroupe="+numGroupe);
            res = state.executeQuery(query);
            while(res.next()) {
                enseignantList.add(res.getInt(1));                 
            }
            for(int i=0 ; i<enseignantList.size();i++){
                int numEns = (int)EnseignantList.get(i);
                ensSeance=seanceEns(numEns);
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
    
