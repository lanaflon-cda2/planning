package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Users;
import com.planning.model.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAO extends DAO<Users> {
    
    public UsersDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Users obj) {
        try {
            PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)");
            prepare.setString(1,obj.getIDUser());
            prepare.setString(2,obj.getMotDePasse());
            prepare.setString(3,obj.getFonction());
            prepare.setInt(4,obj.getNumFiliere());
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
            query = "UPDATE Users SET IDUser = '" + obj.getIDUser() + "', MotDePasse = '" + obj.getMotDePasse() + "', Fonction = '" + obj.getFonction();
            query += "', NumFiliere = " + obj.getNumFiliere();
            query +=  " WHERE IDUser = '" + obj.getIDUser() +"'";
            
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
            query = "UPDATE Users SET IDUser = '" + nouv.getIDUser() + "', MotDePasse = '" + nouv.getMotDePasse();
            query +=  "', Fonction = '" + nouv.getFonction() + "', NumFiliere = " + nouv.getNumFiliere();
            query += " WHERE IDUser = '" + old.getIDUser() + "'";
            state.executeUpdate(query);
	}
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
	}
       
        return true;
    }
    
    
    public ArrayList findALL(){
        ArrayList listusers = null;
        Users users = null;
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT IDUser, MotDePasse, Fonction, Users.NumFiliere, NomFiliere FROM Users, Filiere WHERE Filiere.NumFiliere = Users.NumFiliere";
            query += " UNION (SELECT IDUser, MotDePasse, Fonction, NumFiliere, '' FROM Users WHERE NumFiliere = 0)";
            res = state.executeQuery(query);
            while(res.next()) {
                if(listusers == null)  listusers = new ArrayList();
                users = new Users(res.getString(1), res.getString(2), res.getString(3), res.getInt(4));   
                users.setNomFiliere(res.getString(5));
                EnseignantDAO enseignantDAO = new EnseignantDAO(this.conn);
                Enseignant enseignant = enseignantDAO.findByIDUser(res.getString(1));
                users.setEnseignant(enseignant);
                listusers.add(users);
            }   
        }catch (SQLException e) {  
            System.out.println("SQLException: " + e);
            return null;
        }
        return listusers;
    }
    
    public Users find(String iDUser){
        Users users = null;
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT IDUser, MotDePasse, Fonction, Users.NumFiliere, NomFiliere FROM Users, Filiere WHERE Filiere.NumFiliere = Users.NumFiliere and IDUser = '" + iDUser + "'";
            res = state.executeQuery(query);
            while(res.next()) {
                users = new Users(res.getString(1), res.getString(2), res.getString(3), res.getInt(4));   
                users.setNomFiliere(res.getString(5));
                EnseignantDAO enseignantDAO = new EnseignantDAO(this.conn);
                Enseignant enseignant = enseignantDAO.findByIDUser(res.getString(1));
                users.setEnseignant(enseignant);
            }
            if(users == null) {
                query = "SELECT IDUser, MotDePasse, Fonction, Users.NumFiliere, '' FROM Users WHERE IDUser = '" + iDUser + "'";
                res = state.executeQuery(query);
                while(res.next()) {
                    users = new Users(res.getString(1), res.getString(2), res.getString(3), res.getInt(4));   
                    users.setNomFiliere(res.getString(5));
                    EnseignantDAO enseignantDAO = new EnseignantDAO(this.conn);
                    Enseignant enseignant = enseignantDAO.findByIDUser(res.getString(1));
                    users.setEnseignant(enseignant);
                }
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
