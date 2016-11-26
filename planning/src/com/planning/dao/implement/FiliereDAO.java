package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Filiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FiliereDAO extends DAO<Filiere> {
    
    public FiliereDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Filiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            query = "INSERT INTO Filiere VALUES (NULL, ";
            query += "'" + obj.getNomFiliere() + "')";
            
            int numFiliere = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            obj.setNumFiliere(numFiliere);            
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delete(Filiere obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "DELETE FROM Filiere WHERE NumFliere = " + obj.getNumFiliere();
            state.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Filiere obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Filiere SET NomFiliere = '" + obj.getNomFiliere() + "', WHERE NumFilere = " + obj.getNumFiliere();
            state.executeUpdate(query);
	}
        catch (SQLException e) {
	    System.out.println("SQLException: " + e);
            return false;
	}
        return true;
    }
    
    @Override
    public Filiere find(int numf){
        
        Filiere filiere = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Filiere WHERE NumFiliere = " + numf;
            res = state.executeQuery(query);
            if(res.first()) {
                filiere = new Filiere(res.getInt(1), res.getString(2));   
            }   
        }catch (SQLException e) {   
            System.out.println("SQLException: " + e);
            return null;       
        }
        return filiere;
    }
    
    
    @Override
    public Filiere find(String string){

        return null;
    }
}
