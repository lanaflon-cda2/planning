package com.planning.dao.implement;

import com.planning.dao.DAO;
import com.planning.model.Creneau;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;

public class CreneauDAO extends DAO<Creneau> {
        
    public CreneauDAO (Connection conn) {
        super(conn);
    }
    
    @Override
    public boolean create(Creneau obj) {
        
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 
            query = "INSERT INTO Creneau VALUES (NULL, '";
            query += obj.getDateCreneau() + "','";
            query += obj.getHeureCreneau() + "')";
            int numCreneau = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            obj.setNumCreneau(numCreneau);
            
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        
        return true;
        
    }
    
    
    @Override
    public boolean delete(Creneau obj){
        try {
            query = "DELETE FROM Creneau WHERE NumCreneau = " + obj.getNumCreneau();
            
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        } 
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Creneau obj){
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            query = "UPDATE Creneau SET DateCreneau = " + obj.getDateCreneau()+ ", HeureCreneau = " + obj.getHeureCreneau();
            query += " WHERE NumSeance = '" + obj.getNumCreneau();
            state.executeUpdate(query);
	}
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public Creneau find(int numcreneau){
        
        Creneau creneau = null;
        
        try {
            state = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            query = "SELECT * FROM Creneau WHERE NumCreneau = " + numcreneau;
            res = state.executeQuery(query);
            while(res.next()) {
                creneau = new Creneau(res.getInt(1), res.getDate(2), res.getTime(3));                
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return null;
        }
        return creneau;
    }
    
    @Override
    public Creneau find(String string){
            return null;
    }

    public void insererAllCreneau(int yearDebut, int yearFin){
        
        Creneau x;
        Calendar cal = Calendar.getInstance();
        int[] month = {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6};
        int nbJours;
        int year;
        long dateToMillisSecond;
        Date dateToInsert;
        Time timeToInsert;
        for(int j: month) {
            if(j == 8 || j == 9 || j == 10 || j == 11) { 
                year = yearDebut;
            } else year = yearFin;
            
            nbJours = this.daysInMonth(j + 1, year);
            
            for(int i = 1; i <= nbJours; i++){


                cal.set(year, j, i);

                if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                    cal.set(year, j, i, 8, 00, 00);
                    dateToMillisSecond = cal.getTimeInMillis();

                    dateToInsert = new Date(dateToMillisSecond);
                    timeToInsert = new Time(dateToMillisSecond);

                    x = new Creneau(dateToInsert, timeToInsert);

                    if(this.create(x)) {
                        System.out.println("Creneau " + dateToInsert + " " + timeToInsert + " " + "correctement insere");
                    } else System.out.println("Error pour le creneau " + dateToInsert + " " + timeToInsert);

                    cal.set(year, j, i, 10, 00, 00);
                    dateToMillisSecond = cal.getTimeInMillis();

                    dateToInsert = new Date(dateToMillisSecond);
                    timeToInsert = new Time(dateToMillisSecond);

                    x = new Creneau(dateToInsert, timeToInsert);

                    if(this.create(x)) {
                        System.out.println("Creneau " + dateToInsert + " " + timeToInsert + " " + "correctement insere");
                    } else System.out.println("Error pour le creneau " + dateToInsert + " " + timeToInsert);

                    cal.set(year, j, i, 14, 00, 00);
                    dateToMillisSecond = cal.getTimeInMillis();

                    dateToInsert = new Date(dateToMillisSecond);
                    timeToInsert = new Time(dateToMillisSecond);

                    x = new Creneau(dateToInsert, timeToInsert);

                    if(this.create(x)) {
                        System.out.println("Creneau " + dateToInsert + " " + timeToInsert + " " + "correctement insere");
                    } else System.out.println("Error pour le creneau " + dateToInsert + " " + timeToInsert);

                    cal.set(year, j, i, 16, 00, 00);
                    dateToMillisSecond = cal.getTimeInMillis();

                    dateToInsert = new Date(dateToMillisSecond);
                    timeToInsert = new Time(dateToMillisSecond);

                    x = new Creneau(dateToInsert, timeToInsert);

                    if(this.create(x)) {
                        System.out.println("Creneau " + dateToInsert + " " + timeToInsert + " " + "correctement insere");
                    } else System.out.println("Error pour le creneau " + dateToInsert + " " + timeToInsert);

                }
            }
        
        }
    
    }
    
    private int daysInMonth(int mois, int year) {
    
        switch (mois) {
            case 1:
                return 31;
            case 2:
               //vérifier si l'année est bixectille
               if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                   return 29;
               } else {
                   return 28;
               }
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            default:
                return 31;
        }
        
    
    }
    
}
