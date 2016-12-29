package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Users;
import com.planning.model.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO extends DAO<Users> {
    
    public UsersDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Users obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO Users VALUES (?,?)");
            prepare.setString(1,obj.getIDUser());
            prepare.setString(2,obj.getMotDePasse());
            prepare.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
            
        }

        return true;
    }
    
    @Override
    public boolean delete(Users obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "DELETE FROM Users WHERE IDUser = '" + obj.getIDUser() + "'";
            state.executeUpdate(query);
        } 
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Users obj){
        try {
            
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Users SET IDUser = '" + obj.getIDUser() + "', MotDePasse = '" + obj.getMotDePasse() + "' WHERE IDUser = '" + obj.getIDUser()+"'";
            state.executeUpdate(query);
	}
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
	}
        return true;
    }
    
    public boolean update(Users old, Users nouv) {
        try {
            
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Users SET IDUser = '" + nouv.getIDUser() + "', MotDePasse = '" + nouv.getMotDePasse() + "' WHERE IDUser = '" + old.getIDUser() + "'";
            state.executeUpdate(query);
	}
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
	}
       
        return true;
    }
    @Override
    public Users find(String iDUser){
        Users users = null;
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Users WHERE IDUser = '" + iDUser + "'";
            res = state.executeQuery(query);
            if(res.next()) {
                users = new Users(res.getString("IDUser"), res.getString("MotDePasse"));   
                EnseignantDAO enseignantDAO = new EnseignantDAO(this.conn);
                Enseignant enseignant = enseignantDAO.findByIDUser(res.getString(1));
                users.setEnseignant(enseignant);
             }   
        }catch (SQLException e) {  
            System.out.println("SQLException: " + e);
            return null;
        }
        return users;
    }
    
    
    @Override
    public Users find(int id) {
        return null;
    }
    
    
}
