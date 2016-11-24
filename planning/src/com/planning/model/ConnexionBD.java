/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnexionBD {
    
    public static Connection init(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/planning_bd","root","");
            if (conn != null)
                System.out.println("Connexion Reussite");
            else
                System.out.println("Erreur de Connexion");
        
                
        }catch(Exception e){
            System.out.println("--> SQLException : " + e);
        }
        
        return conn;
    }
     /*public static void main(String args[]){
         ConnexionBD.init();
     }*/
    
}
