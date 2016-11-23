package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Enseignant;
import com.planning.model.Seance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnseignantDAO extends DAO<Enseignant> {
    
    public EnseignantDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Enseignant obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            String query = "INSERT INTO Enseignant VALUES (NULL, ";
            query += "'" + obj.getNomEns() + "', ";
            query += "'" + obj.getPrenomEns() + "',";
            query += "'" + obj.getMail() + "', ";
            query += obj.getTel() + ", ";
            query += "'" + obj.getIDUser() + "')";
            
            int numEns = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            obj.setNumEns(numEns);            
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    
    public boolean delete(Enseignant obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Enseignant WHERE NumEns = " + obj.getNumEns());
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    public classe EnsSeance {

    int numEns = 
}
                }
            }
        }
        return true;
    }
    
    public boolean update(Enseignant obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Enseignant SET "
                +" NomEns = "  + obj.getNomEns()+ ",'"
                        +" PrenomEns = " + obj.getPrenomEns()+ ",'"
                                +" Mail = " + obj.getMail()+ ",'"
                                        +" Tel = " + obj.getTel()+ ",'"
                                                +" NumUser = " + obj.getIDUser()
                                                        + " WHERE NumEns = '" + obj.getNumEns());
            obj = this.find(obj.getNumEns());
	}
        catch (SQLException e) {
	            e.printStackTrace();
                    return false;
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
    
    public Enseignant find(int numens){
        
        Enseignant enseignant = null;
        
        try {
            state = this.conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Enseignant WHERE NumEns = " + numens);
            res = state.executeQuery(query);
            if(res.next()) {
                enseignant = new Enseignant(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumEns(res.getInt(0));
                Iterator iterator = seanceList.iterator();
            while(iterator.hasNext()){
                enseignant.addSeance((Seance)iterator.next());
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
            if(conn != null){
                try{
                conn.close();
                }
                catch(SQLException e){    
                }
            }
        }
        return enseignant;
    }
    
    
    public Enseignant findByIDUser(String iDUser){
        
        Enseignant enseignant = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Enseignant WHERE IDUsers = " + iDUser);
            res = state.executeQuery(query);
            while(res.next()) {
                enseignant = new Enseignant(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5));                  
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
        return enseignant;
    }
    
    
    
    public Enseignant finds(String string){
      
        return null;
    }
}
