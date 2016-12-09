/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.StatiqueCreneau;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author genereux
 */
public class StatiqCrenoDAO  extends DAO <StatiqueCreneau> {

    public StatiqCrenoDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(StatiqueCreneau obj) {
        return false;
    }

    @Override
    public boolean delete(StatiqueCreneau obj) {
        return false;
    }

    @Override
    public boolean update(StatiqueCreneau obj) {
        return false;
    }

    @Override
    public StatiqueCreneau find(int id) {
        return null;
    }

    @Override
    public StatiqueCreneau find(String string) {
        return null;
    }
    
    public ArrayList getAllSC(){
        
        ArrayList allSC = null;
        StatiqueCreneau sc;
        try {
            state = conn.createStatement();
            query = "Select * FROM StatiqueCreneau";
            res = state.executeQuery(query);
            while(res.next()) {
                sc = new StatiqueCreneau(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getInt(5), res.getTime(6), res.getDate(7), res.getDate(8));
                allSC.add(sc);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
        return allSC;
    }
    
}
