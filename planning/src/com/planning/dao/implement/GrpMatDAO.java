/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.GroupeMatiere;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author genereux
 */
public class GrpMatDAO extends DAO<GroupeMatiere> {

    public GrpMatDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(GroupeMatiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            query = "INSERT INTO GroupeMatiere   VALUES (";
            query += obj.getNumMatiere() + ", ";
            query += obj.getNumGroupe()+ ", ";
            query += "'" + obj.getDateDebut()+ "', '";
            query += obj.getDateFin()+ "')";
            
            state.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println("SQLException in creating GroupeMatiere: " + e);
            return false;
        }
       
        return true;
    }

    @Override
    public boolean delete(GroupeMatiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "DELETE FROM GroupeMatiere WHERE NumMatiere = " + obj.getNumMatiere() + " and NumGroupe = " + obj.getNumGroupe();
            state.executeUpdate(query);
        } 
        catch (SQLException e) {
            System.out.println("SQLException in deleting GroupeMatiere: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(GroupeMatiere obj) {
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE GroupeMatiere SET DateDebut = '" + obj.getDateDebut() + "', DateFin = '" + obj.getDateFin() + "' WHERE NumMatiere = " 
                    + obj.getNumMatiere() + " and NumGroupe = " + obj.getNumGroupe();
            state.executeUpdate(query);
        } 
        catch (SQLException e) {
            System.out.println("SQLException in updating GroupeMatiere: " + e);
            return false;
        }
        return true;
    }

    @Override
    public GroupeMatiere find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupeMatiere find(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
