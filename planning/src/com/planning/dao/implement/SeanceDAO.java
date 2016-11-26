package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Seance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.sql.Time;
import java.sql.Date;

public class SeanceDAO extends DAO<Seance> {
        
    public SeanceDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Seance obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumSeance') as numseance");
            res = state.executeQuery(query1);
            if(res.first()) {
                int numseance = res.getInt(0);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO SEANCE (NumSeance,Numcreneau,NumEns, NumMatiere, NumGroupe, NumFiliere, EtatSeance) VALUES (?,?,?,?,?,?,?,?)");
                prepare.setInt(1,numseance);
                prepare.setInt(2,obj.getnumCreneau());
                prepare.setInt(3,obj.getNumEns());
                prepare.setInt(4,obj.getNumMatiere());
                prepare.setInt(5,obj.getNumGroupe());
                prepare.setInt(6,obj.getNumFiliere());
                prepare.setInt(7,obj.getEtatSeance());
                prepare.executeUpdate();
                obj = this.find(numseance);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean delete(Seance obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Seance WHERE NumSeance = " + obj.getNumSeance());
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean update(Seance obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Seance SET "
                        +"NumCreneau ="+obj.getnumCreneau()+",'"
                                    +" NumEns = " + obj.getNumEns()+ ",'"
                                            +" NumMatiere = " + obj.getNumMatiere()+ ",'"
                                                    +" NumGroupe = " + obj.getNumGroupe()+ ",'"
                                                            +" NumFiliere = " + obj.getNumFiliere()+ ",'"
                                                                    +" EtatSeance = " + obj.getEtatSeance()
                                                                            + " WHERE NumSeance = '" + obj.getNumSeance());
            obj = this.find(obj.getNumSeance());
	}
        catch (SQLException e) {
	            e.printStackTrace();
	}
        return true;
    }
    
    public Seance find(int numseance){
        
        Seance seance = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Seance WHERE NumSeance = " + numseance);
            res = state.executeQuery(query);
            if(res.first()) {
                seance = new Seance(res.getInt(0), res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getInt(5), res.getInt(6));                
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return seance;
    }
    
    
    
    public Set<Seance> findByNumEns(int nume){
        
        Set<Seance> seanceList = new HashSet<Seance>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Seance WHERE NumEns = " + nume);
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("numSeance"),res.getInt("numCreneau"),res.getInt("etatSeance"), res.getInt("numEns"), res.getInt("numFiliere"), res.getInt("numGroupe"), res.getInt("numMatiere"));
                seanceList.add(seance);                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seanceList;
    }
    
    
    public Set<Seance> findByNumFiliere(int numf){
        
        Set<Seance> seanceList = new HashSet<Seance>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Seance WHERE NumFiliere = " + numf);
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("numSeance"),res.getInt("numCreneau"),res.getInt("etatSeance"), res.getInt("numEns"), res.getInt("numFiliere"), res.getInt("numGroupe"), res.getInt("numMatiere"));
                seanceList.add(seance);                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seanceList;
    }
    
    
    
    public Set<Seance> findByNumGroupe(int numg){
        
        Set<Seance> seanceList = new HashSet<Seance>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Seance WHERE NumGroupe = " + numg);
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("numSeance"),res.getInt("numCreneau"),res.getInt("etatSeance"), res.getInt("numEns"), res.getInt("numFiliere"), res.getInt("numGroupe"), res.getInt("numMatiere"));
                seanceList.add(seance);                  
            }
            return seanceList;
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return seanceList;
    }
  
    
    
    public Set<Seance> findByNumMatiere(int numm){
        
        Set<Seance> seanceList = new HashSet<Seance>();
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Seance WHERE NumMatiere = " + numm);
            res = state.executeQuery(query);
            while(res.next()) {
                Seance seance = new Seance(res.getInt("numSeance"),res.getInt("numCreneau"),res.getInt("etatSeance"), res.getInt("numEns"), res.getInt("numFiliere"), res.getInt("numGroupe"), res.getInt("numMatiere"));
                seanceList.add(seance);                  
            }
            return seanceList;
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return seanceList;
    }
    
    
    @Override
    public Seance find(String string){
       
        return null;
    }
}
