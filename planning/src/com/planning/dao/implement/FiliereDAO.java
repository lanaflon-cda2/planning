package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Filiere;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

public class FiliereDAO extends DAO<Filiere> {
    
    public FiliereDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Filiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumFiliere') as numfiliere");
            res = state.executeQuery(query1);
            if(res.first()) {
                int numfiliere = res.getInt(1);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO FILIERE (NumFiliere, NomFiliere) VALUES (?,?)");
                prepare.setInt(1,numfiliere);
                prepare.setString(2,obj.getNomFiliere());
                prepare.executeUpdate();
                obj = this.find(numfiliere);
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
    
    public boolean delete(Filiere obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM FILIERE WHERE NumFliere = " + obj.getNumFiliere());
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
    
    public boolean update(Filiere obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE FILIERE SET "
                    +" NomFiliere = " + obj.getNomFiliere()+ ",'"
                            + " WHERE NumFilere = '" + obj.getNumFiliere());
            obj = this.find(obj.getNumFiliere());
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
    
    public Filiere find(int numf){
        
        Filiere filiere = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Filiere WHERE NumFiliere = " + numf);
            res = state.executeQuery(query);
            if(res.first()) {
                filiere = new Filiere(res.getInt(1), res.getString(2));   
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumFiliere(res.getInt(1));
                Iterator iterator = seanceList.iterator();
                while(iterator.hasNext()){
                    filiere.addSeance((Seance)iterator.next());
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
        return filiere;
    }
    
    
    public Filiere finds(String string){
        Filiere filiere = null;
        return filiere;
    }
}
