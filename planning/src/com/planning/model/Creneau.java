/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Oumaima
 */
public class Creneau {
    
    private int numCreneau;
    private Date date;
    private Time heure;
    private Set<Seance> seanceList = new HashSet<Seance> ();
    
   public Creneau(int numCreneau, Date date, Time heure){
        this.numCreneau = numCreneau;
        this.date = date;
        this.heure= heure;
   
   }
   
    public Creneau(){}

    public Creneau(int numCreneau) {
        this.numCreneau = numCreneau;
    }

    public int getNumCreneau() {
        return numCreneau;
    }   
    
    public void setNumCreneau(int numCreneau) {
        this.numCreneau = numCreneau;
    }
    
    public Date getDateCreneau() {
        return date;
    }

    public void setDateCreneau(Date dateCreneau) {
        this.date = dateCreneau;
    }

    public Time getHeureCreneau() {
        return heure;
    }

    public void setHeureCreneau(Time heureCreneau) {
        this.heure = heureCreneau;
    }
    
     public Set<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(Set<Seance> seanceList) {
        this.seanceList = seanceList;
    }
    
    public void addSeance(Seance seance){
        if(!this.seanceList.contains(seance)) this.seanceList.add(seance);
    }
    
    public void removeSeance(Seance seance){
        this.seanceList.remove(seance);
        
    }
    public boolean equals(Creneau creneau){
        return this.getNumCreneau() == creneau.getNumCreneau();
    }



    public String toString() {
        return "com.planning.model.Creneau[ numCreneau=" + numCreneau + " ]";
    }
       
}
