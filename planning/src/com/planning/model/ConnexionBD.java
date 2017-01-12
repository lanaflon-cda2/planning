/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnexionBD {
    private static Connection conn = null;
    
    public static Connection init(){
        return getInstance();
    }
    
    private ConnexionBD () {
        Connection x = null;
        boolean f = false;
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            x = DriverManager.getConnection("jdbc:derby:testad;create=true","root","root");
            /*if (conn != null)
                System.out.println("Connexion A la base de donnees reusies.");*/
            if(x == null)
                System.out.println("Erreur de Connexion");
            String derby = new Scanner(new File("derby.log")).useDelimiter("\\Z").next();
            f = true;
            conn = DriverManager.getConnection("jdbc:derby:testad;create=true","root","root");
        }catch(ClassNotFoundException | SQLException | FileNotFoundException e){
            System.out.println("--> Exception in ConnexionBD.setConn: " + e);
            //return x;
        } finally {
            try{
                if(!f) {
                    Statement stat = x.createStatement();
                String content = new Scanner(new File("sqlfin.txt")).useDelimiter("\\Z").next();
                String req[] = content.split("delimiter");
                String create = req[0];
                String insert = req[1];

                String eachcreate[] = create.split("espace");
                for(int i = 0; i < eachcreate.length - 1; i++) {
                    System.out.println(eachcreate[i]);
                    stat.execute(eachcreate[i]);
                    System.out.println("executed");
                }
                String eachinsert[] = insert.split("espace");
                for(int i = 0; i < eachinsert.length - 1; i++) {
                    System.out.println(eachinsert[i]);
                    stat.executeUpdate(eachinsert[i]);
                    System.out.println("executed");
                }

                //stat.execute(content);

                ResultSet res = stat.executeQuery("select * from APP.CRENEAU");
                while(res.next()) {
                    System.out.println(res.getDate(2));

                }
            }
            
            
            }catch(SQLException | FileNotFoundException e){
                System.out.println("--> Exception in ConnexionBD.setConn: " + e);
            //return x;
        }
        }
        
    }
    
    private static Connection getInstance() {
        if(conn == null){
            new ConnexionBD();
        }
        return conn;
    }
    
    public static void main(String args[]){
        //System.out.println(ConnexionBD.init());
//        try {
//            String content = new Scanner(new File("src/sqlfin.txt")).useDelimiter("\\Z").next();
//            System.out.println(content);
//        }catch(Exception e) {
//            System.out.println("error: " + e);
//        }
//        Path p = Paths.get("src/sqlfin.txt");
//        System.out.println(p.getParent());
//            try{
//               String content = new Scanner(new File("src/sqlfin.txt")).useDelimiter("\\Z").next();
//            String req[] = content.split("delimiter");
//            String create = req[0];
//            String insert = req[1];
//            
//            String eachcreate[] = create.split("espace");
//            for(int i = 0; i < eachcreate.length - 1; i++) {
//                System.out.println(eachcreate[i]);
//                //stat.execute(eachcreate[i]);
//            }
//            String eachinsert[] = insert.split("espace");
//            for(int i = 0; i < eachinsert.length - 1; i++) {
//                System.out.println(eachinsert[i]);
//                //stat.executeUpdate(eachinsert[i]);
//            }
//            
//            }catch(FileNotFoundException e){
//            System.out.println("--> Exception in ConnexionBD.setConn: " + e);
//        }
     }
    
}
