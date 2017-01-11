package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Groupe;
import com.planning.model.Seance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

public class GroupeDAO extends DAO<Groupe> {
    
    public GroupeDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Groupe obj) {
        try {
            state = conn.createStatement();
            query = "INSERT INTO APP.Groupe VALUES (DEFAULT, ";
            query += obj.getNumFiliere() + ", '";
            query += obj.getNomGroupe() + "',  ";
            query += obj.getNiveau() + ")";
            
            int numGroupe = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
        }
        catch (SQLException e) {
            System.out.println("SQLException in GroupeDAO.create: " + e);
            return false;
        }    
        return true;
    }
    
    @Override
    public boolean delete(Groupe obj){
        try {
            this.conn.createStatement().executeUpdate("DELETE FROM APP.Groupe WHERE NumGroupe = " + obj.getNumGroupe());
        } 
        catch (SQLException e) {
            System.out.println("SQLException in GroupeDAO.delete: " + e);
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public boolean update(Groupe obj){
        try {
            state = conn.createStatement();
            query = "UPDATE APP.Groupe SET NomGroupe = " + obj.getNomGroupe() + ", NumFiliere = " + obj.getNumFiliere() + ", Niveau = " + obj.getNiveau();
            query += " WHERE NumGroupe = " + obj.getNumFiliere();
	}
        catch (SQLException e) {
            System.out.println("SQLException in GroupeDAO.update: " + e);
            return false;
        }
        return true;
    }
    
    public ResultSet findALL(){

        try{
            state = conn.createStatement();
            query = "select NumGroupe, NomGroupe, APP.Groupe.NumFiliere as NumFiliere, NomFiliere, Niveau from APP.Groupe, APP.Filiere WHERE APP.Groupe.NumFiliere = APP.Filiere.NumFiliere";
            res = state.executeQuery(query);

        }catch(Exception e){
            System.out.println("SQLException in GroupeDAO.findALL: " + e);
            return null;
        }

        return res;
        
    }
	
    public boolean updatebyObj(Groupe obj1, Groupe obj2) {
        
        try {
            state = this.conn.createStatement();
            query = "UPDATE APP.Groupe SET NomGroupe = '" + obj2.getNomGroupe() + "', NumFiliere = " + obj2.getNumFiliere() + ", Niveau = " + obj2.getNiveau();
            query += " WHERE NomGroupe = '" + obj1.getNomGroupe() + "' AND NumFiliere = " + obj1.getNumFiliere() + " and Niveau = " + obj1.getNiveau();
        } catch (Exception e) {
            System.out.println("SQLException in GroupeDAO.updateByObj: " + e);
            return false;
        }
        return true;
    }
    
    
    @Override
    public Groupe find(int numg){
        
        Groupe groupe = null;
        
        try {    
            state = conn.createStatement();
            query = "SELECT * FROM APP.Groupe WHERE NumGroupe = " + numg;
            res = state.executeQuery(query);
            while(res.next()) {
                groupe = new Groupe(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4));   
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumGroupe(res.getInt(1));
                Iterator iterator = seanceList.iterator();
                while(iterator.hasNext()){
                    groupe.addSeance((Seance)iterator.next());
                }
             }   
        }catch (SQLException e) {   
            System.out.println("SQLException in GroupeDAO.find: " + e);
        }
        return groupe;
    }
    
    
    @Override
    public Groupe find(String string){
       
        return null;
    }
}

