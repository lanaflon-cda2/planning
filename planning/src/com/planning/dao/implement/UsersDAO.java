package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Users;
import com.planning.model.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

public class UsersDAO extends DAO<Users> {
    
    public UsersDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Users obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('IDUser') as IDUser");
            res = state.executeQuery(query1);
            if(res.first()) {
                String IDUser = res.getString(1);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO USERS (IDUser, MotDePasse) VALUES (?,?)");
                prepare.setString(1,IDUser);
                prepare.setString(2,obj.getMotDePasse());
                prepare.executeUpdate();
                obj = this.finds(IDUser);
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
    
    public boolean delete(Users obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM USERS WHERE IDUser = " + obj.getIDUser());
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
    
    public boolean update(Users obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE USERS SET "
                    +" MotDePasse = " + obj.getMotDePasse() + ",'"
                            + " WHERE IDUser = '" + obj.getIDUser()+";");
            obj = this.finds(obj.getIDUser());
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
    
    
    
    public Users find(int id){
        Users users = null;
        return users;
    }
    
    
        
    public Users finds(String iDUser){
        
        Users users = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM USERS WHERE IDUsers = " + iDUser);
            res = state.executeQuery(query);
            if(res.first()) {
                users = new Users(res.getString(1));   
                EnseignantDAO enseignantDAO = new EnseignantDAO(this.conn);
                Enseignant enseignant = enseignantDAO.findByIDUser(res.getString(1));
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
        return users;
    }
    
}
