/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author genereux
 */
public class TestDate {

    public static void testdate() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2016, 11, 30);
         System.out.println(cal1.toString());
        long d = cal1.getTimeInMillis();
         System.out.println(d);
        String s = String.valueOf(d);
        s = s.substring(0, 8);
        s += "00000";
        System.out.println(s);
        try {
            d = Long.parseLong(s);
        }
        catch (Exception e) {
            System.out.println("" + e);
        }
        System.out.println("d " + d);
        cal1.setTimeInMillis(d);
        System.out.println(cal1.toString());

    }
    
    public static void testSplit(){
        String s = "mqdkqf mqdfkmqld";
        String[] t = s.split(" ");
        System.out.println(t[0] + " " + t[1]);
    }

//    public static void main(String args[]) {
//        testSplit();
        //testdate();
//       String t[][] = new String[4][4];
//       String tab[] = new String[4];
//       tab[1] = 20 + "";
//       tab[3] = 60 + "";
//       t[1] = tab;
//        System.out.println("" + t + " " + t.toString() + " " + t.length);
//        //for (int i = 0; i < t.length; i++) {
//            String j = t[0][2];
//            System.out.println("" + j);
//            
//        //}
//    }
}
