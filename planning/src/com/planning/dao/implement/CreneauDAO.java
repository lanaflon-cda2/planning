package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Creneau;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.sql.Time;
import java.sql.Date;

public class CreneauDAO extends DAO<Creneau> {
        
    public CreneauDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Creneau obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumCreneau') as numcreneau");
            res = state.executeQuery(query1);
            if(res.first()) {
                int numcreneau = res.getInt(0);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO Creneau (NumCreneau, Date , Heure) VALUES (?,?,?)");
                prepare.setInt(1,numcreneau);
                prepare.setDate(2, (Date) obj.getDateCreneau());
                prepare.setTime(3,(Time) obj.getHeureCreneau());
                prepare.executeUpdate();
                obj = this.find(numcreneau);
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
    
    public boolean delete(Creneau obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Creneau WHERE NumCreneau = " + obj.getNumCreneau());
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
    
    public boolean update(Creneau obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Creneau SET "
                    +" DateCreneau = " + obj.getDateCreneau()+ ",'"
                            +" HeureCreneau = " + obj.getHeureCreneau()
                                                                        + " WHERE NumSeance = '" + obj.getNumCreneau());
            obj = this.find(obj.getNumCreneau());
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
    
    public Creneau find(int numcreneau){
        
        Creneau creneau = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Creneau WHERE NumCreneau = " + numcreneau);
            res = state.executeQuery(query);
            if(res.first()) {
                creneau = new Creneau(res.getInt(0), res.getDate(1), res.getTime(2));                
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
        return creneau;
    }
    
public Creneau finds(String string){
        Creneau creneau = null;
        return creneau;
    }    

}
