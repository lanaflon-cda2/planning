package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Matiere;
import com.planning.model.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.sql.Time;
import java.sql.Date;

public class MatiereDAO extends DAO<Matiere> {
    
    public MatiereDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Matiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumMatiere') as nummatiere");
            res = state.executeQuery(query1);
            if(res.first()) {
                int nummatiere = res.getInt(0);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO Matiere (NumMatiere, NomMatiere VALUES (?,?)");
                prepare.setInt(1,nummatiere);
                prepare.setString(2,obj.getNomMatiere());
                prepare.executeUpdate();
                obj = this.find(nummatiere);
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
    
    public boolean delete(Matiere obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM MATIERE WHERE NumMatiere = " + obj.getNumMatiere());
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
    
    public boolean update(Matiere obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE MATIERE SET "
                    +" NomMatiere = " + obj.getNomMatiere()+ ",'"
                                            + " WHERE NumMatiere = '" + obj.getNumMatiere());
            obj = this.find(obj.getNumMatiere());
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
    
    public Matiere find(int numm){
        
        Matiere matiere = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Matiere WHERE NumMatiere = " + numm);
            res = state.executeQuery(query);
            if(res.first()) {
                matiere = new Matiere(res.getInt(0));   
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumFiliere(res.getInt(0));
                Iterator iterator = seanceList.iterator();
                while(iterator.hasNext()){
                    matiere.addSeance((Seance)iterator.next());
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
        return matiere;
    }
    
    
    @Override
    public Matiere find(String string){
        
        return null;
    }
}
