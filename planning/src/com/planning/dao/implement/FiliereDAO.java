package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Filiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FiliereDAO extends DAO<Filiere> {
    
    public FiliereDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Filiere obj) {
        try {
            state = conn.createStatement();
            
            query = "INSERT INTO APP.Filiere VALUES (DEFAULT, ";
            query += "'" + obj.getNomFiliere() + "')";
            
            int numFiliere = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            obj.setNumFiliere(numFiliere);            
        }
        catch (SQLException e) {
            System.out.println("SQLException in Filiere.create: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delete(Filiere obj){
        try {
            state = conn.createStatement();
            query = "DELETE FROM APP.Filiere WHERE NumFliere = " + obj.getNumFiliere();
            state.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println("SQLException in Filiere.delete: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Filiere obj){
        try {
            state = conn.createStatement();
            query = "UPDATE APP.Filiere SET NomFiliere = '" + obj.getNomFiliere() + "', WHERE NumFilere = " + obj.getNumFiliere();
            state.executeUpdate(query);
	}
        catch (SQLException e) {
	    System.out.println("SQLException in Filiere.update: " + e);
            return false;
	}
        return true;
    }
    
    @Override
    public Filiere find(int numf){
        
        Filiere filiere = null;
        
        try {    
            state = conn.createStatement();
            query = "SELECT * FROM APP.Filiere WHERE NumFiliere = " + numf;
            res = state.executeQuery(query);
            while(res.next()) {
                filiere = new Filiere(res.getInt(1), res.getString(2));   
            }   
        }catch (SQLException e) {   
            System.out.println("SQLException in Filiere.find: " + e);
            return null;       
        }
        return filiere;
    }
    
      public ArrayList findAll(){
            ArrayList listeFiliere = null;

            try {    
                state = conn.createStatement();
                query = "SELECT * FROM APP.Filiere";
                res = state.executeQuery(query);
                while(res.next()) {
                    if(listeFiliere == null) listeFiliere = new ArrayList();
                    listeFiliere.add(new Filiere(res.getInt(1), res.getString(2)));
                }   
            }catch (SQLException e) {   
                System.out.println("SQLException in Filiere.findALL: " + e);
                return null;       
            }
            return listeFiliere;
    }
    
    public int findNumFiliere(String nomf){
        
        int numf = 0;
        
        try {    
            state = conn.createStatement();
            query = "SELECT numFiliere FROM APP.Filiere WHERE NomFiliere = " + nomf;
            res = state.executeQuery(query);
            if(res.first()) {
                numf = res.getInt(1);   
            }   
        }catch (SQLException e) {   
            System.out.println("SQLException in Filiere.findNumFiliere: " + e);
            return 0;       
        }
        return numf;
    }
        
    @Override
    public Filiere find(String string){

        return null;
    }
}
