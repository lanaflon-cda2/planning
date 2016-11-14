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
                int numseance = res.getInt(1);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO SEANCE (NumSeance, DateSeance, HeurSeance, NumEns, NumMatiere, NumGroupe, NumFiliere, EtatSeance) VALUES (?,?,?,?,?,?,?,?)");
                prepare.setInt(1,numseance);
                prepare.setDate(2, (Date) obj.getDateSeance());
                prepare.setDate(3,(Date) obj.getHeureSeance());
                prepare.setInt(4,obj.getNumEns());
                prepare.setInt(5,obj.getNumMatiere());
                prepare.setInt(6,obj.getNumGroupe());
                prepare.setInt(7,obj.getNumFiliere());
                prepare.setInt(8,obj.getEtatSeance());
                prepare.executeUpdate();
                obj = this.find(numseance);
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
        finally{
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
        }
        return true;
    }
    
    public boolean update(Seance obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Seance SET "
                    +" DateSeance = " + obj.getDateSeance()+ ",'"
                            +" HeureSenace = " + obj.getHeureSeance()+ ",'"
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
        finally{
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
                seance = new Seance(res.getInt(1), res.getDate(2), res.getTime(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8));                
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
                Seance seance = new Seance(res.getInt(1), res.getDate(2), res.getTime(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8));
                seanceList.add(seance);                
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
                Seance seance = new Seance(res.getInt(1), res.getDate(2), res.getTime(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8));
                seanceList.add(seance);                
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
                Seance seance = new Seance(res.getInt(1), res.getDate(2), res.getTime(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8));
                seanceList.add(seance);                  
            }
            return seanceList;
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
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
                Seance seance = new Seance(res.getInt(1), res.getDate(2), res.getTime(3), res.getInt(4), res.getInt(5), res.getInt(6), res.getInt(7), res.getInt(8));
                seanceList.add(seance);                  
            }
            return seanceList;
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
        }
        return seanceList;
    }
    
    
    public Seance finds(String string){
        Seance seance = null;
        return seance;
    }
}
