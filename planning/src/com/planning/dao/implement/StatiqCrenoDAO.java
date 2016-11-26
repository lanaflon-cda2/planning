/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.StatiqueCreneau;
import java.sql.Connection;

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
    
}
