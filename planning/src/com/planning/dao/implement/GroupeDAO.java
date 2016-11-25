package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Filiere;
import com.planning.model.Groupe;
import com.planning.model.Seance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupeDAO extends DAO<Groupe> {
    
    public GroupeDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Groupe obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumGroupe') as numgroupe");
            res = state.executeQuery(query1);
            if(res.first()) {
                int numgroupe = res.getInt(0);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO GROUPE (NumGroupe, NomGroupe, Niveau) VALUES (?,?,?)");
                prepare.setInt(1,numgroupe);
                prepare.setString(2,obj.getNomGroupe());
                prepare.setInt(3,obj.getNiveau());
                prepare.executeUpdate();
                obj = this.find(numgroupe);
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
    
    public boolean delete(Groupe obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM GROUPE WHERE NumGroupe = " + obj.getNumGroupe());
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
    
    public boolean update(Groupe obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE GROUPE SET "
                    +" NomGroupe = " + obj.getNomGroupe()+ ",'"
                            +" Niveau = " + obj.getNiveau()
                                    + " WHERE NumGroupe = '" + obj.getNumGroupe());
            obj = this.find(obj.getNumGroupe());
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
    
    public Groupe find(int numg){
        
        Groupe groupe = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Groupe WHERE NumGroupe = " + numg);
            res = state.executeQuery(query);
            if(res.first()) {
                groupe = new Groupe(res.getInt(0));   
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumGroupe(res.getInt(0));
                Iterator iterator = seanceList.iterator();
                while(iterator.hasNext()){
                    groupe.addSeance((Seance)iterator.next());
                }
             }   
        }catch (SQLException e) {   
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
        return groupe;
    }
    
    
    @Override
    public Groupe find(String string){
       
        return null;
    }
}

