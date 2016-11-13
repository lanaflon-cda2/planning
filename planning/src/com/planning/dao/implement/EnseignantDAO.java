package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Enseignant;
import com.planning.model.Seance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnseignantDAO extends DAO<Enseignant> {
    
    public EnseignantDAO (Connection conn) {
        super(conn);
    }
    
    public boolean create(Enseignant obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query1 = new String("SELECT NEXTVAL ('NumEns') as numens");
            res = state.executeQuery(query1);
            if(res.first()) {
                int numens = res.getInt("NumEns");
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO ENSEIGNANT (NumEns, NomEns, PrenomEns, Mail, Tel, NumUser) VALUES (?,?,?,?,?,?)");
                prepare.setInt(1,numens);
                prepare.setString(2,obj.getNomEns());
                prepare.setString(3,obj.getPrenomEns());
                prepare.setString(4,obj.getMail());
                prepare.setInt(5,obj.getTel());
                prepare.setInt(6,obj.getNumUser());
                prepare.executeUpdate();
                obj = this.find(numens);
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
    
    public boolean delete(Enseignant obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM ENSEIGNANT WHERE NumEns = " + obj.getNumEns());
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
    
    public boolean update(Enseignant obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE ENSEIGNANT SET "
                +" NomEns = "  + obj.getNomEns()+ ",'"
                        +" PrenomEns = " + obj.getPrenomEns()+ ",'"
                                +" Mail = " + obj.getMail()+ ",'"
                                        +" Tel = " + obj.getTel()+ ",'"
                                                +" NumUser = " + obj.getNumUser()
                                                        + " WHERE NumEns = '" + obj.getNumEns());
            obj = this.find(obj.getNumEns());
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
    
    public Enseignant find(int numens){
        
        Enseignant enseignant = new Enseignant();
        
        try {
            state = this.conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Enseignant WHERE NumEns = " + numens);
            res = state.executeQuery(query);
            if(res.first()) {
                enseignant = new Enseignant(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
                SeanceDAO seanceDAO = new SeanceDAO(this.conn);
                Set<Seance> seanceList = seanceDAO.findByNumEns(res.getInt(0));
                Iterator iterator = seanceList.iterator();
            while(iterator.hasNext()){
                enseignant.addSeance((Seance)iterator.next());
                }
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
        return enseignant;
    }
    
    
    public Set<Enseignant> findByIDUser(String iDUser){
        Set<Enseignant> enseignantList = new HashSet<Enseignant>();
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            String query = new String("SELECT * FROM Enseignant WHERE IDUsers = " + iDUser);
            res = state.executeQuery(query);
            while(res.next()) {
                Enseignant enseignant = new Enseignant(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
                enseignantList.add(enseignant);                  
            }
            return enseignantList;
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
        return enseignantList;
    }
    
    
    
    public Enseignant finds(String string){
        Enseignant ensaignant = new Enseignant();
        return ensaignant;
    }
}
