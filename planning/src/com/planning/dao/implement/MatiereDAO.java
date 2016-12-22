package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Matiere;
import com.planning.model.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            query = "SELECT NEXTVAL ('NumMatiere') as nummatiere";
            res = state.executeQuery(query);
            if(res.first()) {
                int nummatiere = res.getInt(1);
                PreparedStatement prepare = this.conn.prepareStatement("INSERT INTO Matiere (NumMatiere, NomMatiere VALUES (?,?)");
                prepare.setInt(1,nummatiere);
                prepare.setString(2,obj.getNomMatiere());
                prepare.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    @Override
    public boolean delete(Matiere obj){
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM MATIERE WHERE NumMatiere = " + obj.getNumMatiere());
        } 
        catch (SQLException e) {
        }
        return true;
    }
    
    @Override
    public boolean update(Matiere obj){
        try {
            this .conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("UPDATE MATIERE SET "
                    +" NomMatiere = " + obj.getNomMatiere()+ ",'"
                                            + " WHERE NumMatiere = '" + obj.getNumMatiere());

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
}
