package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Enseignant;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class EnseignantDAO extends DAO<Enseignant> {
    
    public EnseignantDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Enseignant obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            query = "INSERT INTO Enseignant VALUES (NULL, ";
            query += "'" + obj.getNomEns() + "', ";
            query += "'" + obj.getPrenomEns() + "',";
            query += "'" + obj.getMail() + "', ";
            query += obj.getTel() + ", ";
            query += "'" + obj.getIDUser() + "')";
            
            int numEns = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            obj.setNumEns(numEns);            
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
       
        return true;
    }

    
    @Override
    public boolean delete(Enseignant obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "DELETE FROM Enseignant WHERE NumEns = " + obj.getNumEns();
            state.executeUpdate(query);
        } 
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;

    }    
    @Override
    public boolean update(Enseignant obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Enseignant SET NomEns = '"  + obj.getNomEns()+ "', PrenomEns = '" + obj.getPrenomEns();
            query += "', Mail = '" + obj.getMail()+ "', Tel = " + obj.getTel()+ ", IDUser = '" + obj.getIDUser() + "' WHERE NumEns = " + obj.getNumEns();
                                                        
            state.executeUpdate(query);
	}
        
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
	}
        return true;
    }

    public boolean updateByID(String id){
        try {
            Enseignant obj = new Enseignant(id);
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Enseignant SET NomEns = '"  + obj.getNomEns()+ "', PrenomEns = '" + obj.getPrenomEns();
            query += "', Mail = '" + obj.getMail()+ "', Tel = " + obj.getTel() + " WHERE IDUser = '" + id + "'";
                                                        
            state.executeUpdate(query);
	}
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
	}
        return true;
    }    
    
       
    @Override
    public Enseignant find(int numens){
        
        Enseignant enseignant = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Enseignant WHERE NumEns = " + numens;
            res = state.executeQuery(query);
            if(res.next()) {
                enseignant = new Enseignant(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getLong(5), res.getString(6));
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumEns(res.getInt(1));
                Iterator iterator = seanceList.iterator();
            while(iterator.hasNext()){
                enseignant.addSeance((Seance)iterator.next());
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
        return enseignant;
    }
    
    public Enseignant findByIDUser(String iDUser){
        
        Enseignant enseignant = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Enseignant WHERE IDUser = '" + iDUser+ "'";
            res = state.executeQuery(query);
            while(res.next()) {
                enseignant = new Enseignant(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getLong(5), res.getString(6));                  
            }
        } catch (SQLException e) {
             System.out.println("SQLException: " + e);
            return null;
        }
        
        return enseignant;
    }
    
      public ArrayList findAll(){
            ArrayList listens = null;
            Enseignant ens;
            try {    
                state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
                query = "SELECT * FROM Enseignant";
                res = state.executeQuery(query); 
                while(res.next()) {
                    if(listens == null) listens = new ArrayList();
                    ens = new Enseignant(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getLong(5), res.getString(6));
                    listens.add(ens);
                }
            }catch (SQLException e) {   
                System.out.println("SQLException: " + e);
                return null;       
            }
            return listens;
    }
    
    @Override
    public Enseignant find(String string){
        
        return null;
    }
}
