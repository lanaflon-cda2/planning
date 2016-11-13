package com.planning.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO <T> {
    protected Connection conn = null;
    protected ResultSet res = null;
    protected Statement state = null;
    
    public DAO (Connection conn) {
        this.conn = conn;
    }
    
    public abstract boolean create(T obj);
    
    public abstract boolean delete(T obj);
    
    public abstract boolean update(T obj);
    
    public abstract T find(int id);
    
    public abstract T finds(String string);
}
