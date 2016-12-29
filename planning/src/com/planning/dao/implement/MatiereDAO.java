package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Matiere;
import com.planning.model.Seance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class MatiereDAO extends DAO<Matiere> {
    
    public MatiereDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Matiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "INSERT INTO Matiere VALUES (NULL, '";
            query += obj.getNomMatiere() + "')";
            int numMatiere = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            obj.setNumMatiere(numMatiere);
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    @Override
    public boolean delete(Matiere obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Matiere WHERE NumMatiere = " + obj.getNumMatiere());
        } 
        catch (SQLException e) {
        }
        return true;
    }
    
    @Override
    public boolean update(Matiere obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE Matiere SET "
                    +" NomMatiere = '" + obj.getNomMatiere()+ "' WHERE NumMatiere = " + obj.getNumMatiere());

	}
        catch (SQLException e) {
	}
        return true;
    }
    
    @Override
    public Matiere find(int numm){
        
        Matiere matiere = null;
        
        try {    
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Matiere WHERE NumMatiere = " + numm;
            res = state.executeQuery(query);
            while(res.next()) {
                matiere = new Matiere(res.getInt(1), res.getString(2));   
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumMatiere(res.getInt(1));
                Iterator iterator = seanceList.iterator();
                while(iterator.hasNext()){
                    matiere.addSeance((Seance)iterator.next());
                }
             }   
        }catch (SQLException e) {  
            System.out.println("SQLException: " + e);
        }
        return matiere;
    }
    
    
    @Override
    public Matiere find(String string){
        
        return null;
    }
    
    public ArrayList FindAll(){
        ArrayList listematiere=null;
        try{
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "Select * from Matiere";
            res = state.executeQuery(query);
            while(res.next()){
                if(listematiere==null) listematiere = new ArrayList();
                Matiere matiere = new Matiere(res.getInt(1), res.getString(2));
                listematiere.add(matiere);
                
            }

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        return  listematiere;

        
        
        
    }
        
        
    
}
