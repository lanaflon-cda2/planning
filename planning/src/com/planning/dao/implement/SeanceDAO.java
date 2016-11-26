package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Seance;
import com.planning.model.StatiqueCreneau;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SeanceDAO extends DAO<Seance> {
        
    public SeanceDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Seance obj) {
            try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            query = "INSERT INTO Seance VALUES (NULL, ";
            query += obj.getNumCreneau() + ", ";
            query += obj.getNumEns() + ",";
            query += obj.getNumMatiere() + ", ";
            query += obj.getNumGroupe() + ", 1)";
            
            int numSeance = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            obj.setNumEns(numSeance);            
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
       
        return true;
    }
    
    @Override
    public boolean delete(Seance obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Seance WHERE NumSeance = " + obj.getNumSeance());
        } 
        catch (SQLException e) {
        }
        return true;
    }
    
    @Override
    public boolean update(Seance obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Seance SET "
                        +"NumCreneau ="+obj.getNumCreneau()+",'"
                                    +" NumEns = " + obj.getNumEns()+ ",'"
                                            +" NumMatiere = " + obj.getNumMatiere()+ ",'"
                                                    +" NumGroupe = " + obj.getNumGroupe()+ ",'"
                                                                    +" EtatSeance = " + obj.getEtatSeance()
                                                                            + " WHERE NumSeance = '" + obj.getNumSeance());
	}
        catch (SQLException e) {
	}
        return true;
    }
    
    @Override
    public Seance find(int numseance){
        
        Seance seance = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Seance WHERE NumSeance = " + numseance;
            res = state.executeQuery(query);
            if(res.first()) {
                seance = new Seance(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4));                
            }
        } catch (SQLException e) {
        }
        return seance;
    }
    
    public Set<Seance> findByNumEns(int nume){
        
        Set<Seance> seanceList = new HashSet<>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Seance WHERE NumEns = " + nume;
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("NumSeance"),res.getInt("NumCreneau"), res.getInt("NumEns"), res.getInt("NumMatiere"), res.getInt("NumGroupe"),res.getInt("EtatSeance"));
                seanceList.add(seance);                
            }
        } catch (SQLException e) {
        }
        return seanceList;
    }
        
    public Set<Seance> findByNumGroupe(int numg){
        
        Set<Seance> seanceList = new HashSet<>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Seance WHERE NumGroupe = " + numg;
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("NumSeance"),res.getInt("NumCreneau"), res.getInt("NumEns"), res.getInt("NumMatiere"), res.getInt("NumGroupe"),res.getInt("EtatSeance"));
                seanceList.add(seance);                  
            }
            return seanceList;
        } catch (SQLException e) {
        }
        return seanceList;
    }   
    
    public Set<Seance> findByNumMatiere(int numm){
        
        Set<Seance> seanceList = new HashSet<>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Seance WHERE NumMatiere = " + numm;
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("NumSeance"),res.getInt("NumCreneau"), res.getInt("NumEns"), res.getInt("NumMatiere"), res.getInt("NumGroupe"),res.getInt("EtatSeance"));
                seanceList.add(seance);                  
            }
            return seanceList;
        } catch (SQLException e) {
        }
        return seanceList;
    } 
    @Override
    public Seance find(String string){
       
        return null;
    }
    
    public void insertAllSeanceForStatiqueCreneau(StatiqueCreneau sc) {
        
        Seance s;
        
        int numMatiere = sc.getNumMatiere();
        int numGroupe = sc.getNumGroupe();
        int numEns = sc.getNumEns();
        int dayOfWeek = sc.getJourSemaine();
        Date dateD = sc.getDateD();
        Date dateF = sc.getDateF();
        Date dateDMat = null;
        Date dateFMat = null;
        Time t = sc.getHeureSeance();
        
        String req1 = "Select DateDebut, DateFin FROM GroupeMatiere WHERE NumGroupe = " + numGroupe + " and NumMatiere = " + numMatiere;
        
        
        try {
            state = conn.createStatement();
            res = state.executeQuery(req1);
            while(res.next()) {
                dateDMat = res.getDate(1);
                dateFMat = res.getDate(2);
            }
            if(dateD.before(dateDMat) || dateF.after(dateFMat)) {
                System.out.println("La periode de la seance n'est pas incluse dans la periode de la matiere. Erreur");
                return;
            }
            
            String req2 = "Select NumCreneau , Date FROM Creneau WHERE Date >= '" + dateD + "' and Date <= '" + dateF + "' and Heure = '" + t;
            req2 += "' and DAYOFWEEK(Date) = " + dayOfWeek;
            
            res = state.executeQuery(req2);
            
            while(res.next()) {
                    
                    s = new Seance(res.getInt(1), numEns, numMatiere, numGroupe);
                    if(this.create(s)) System.out.println("Insertion de la seance de " + res.getDate(2));
                    else System.out.println("Error insertion de la seance de " + res.getDate(2));
                    
            }
            
                
        } catch (SQLException e) {
            System.out.println("SQLException :" + e);
        }
        
    }
    
    
    public void insertAllSeance(){
        StatiqCrenoDAO scDAO = new StatiqCrenoDAO(conn);
        ArrayList allSC = scDAO.getAllSC();
        for(Object sc: allSC){
            this.insertAllSeanceForStatiqueCreneau((StatiqueCreneau) sc);
        }
        
        
    }
}
