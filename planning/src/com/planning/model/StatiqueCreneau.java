/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.sql.Date;
import java.sql.Time;


public class StatiqueCreneau {
    
    private int numSC;
    private int numMatiere;
    private int numGroupe;
    private int numEns;
    private int jourSemaine;
    private Time heureSeance;
    private Date dateD;
    private Date dateF;
    
    public StatiqueCreneau() {
    }

    public StatiqueCreneau(int numSC) {
        this.numSC = numSC;
    }
    
    public StatiqueCreneau(int numSC, int numMatiere, int numGroupe, int numEns, int jourSemaine, Time heureSeance, Date dateD, Date dateF) {
        this.numSC = numSC;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.numEns = numEns;
        this.jourSemaine = jourSemaine;
        this.heureSeance = heureSeance;
        this.dateD = dateD;
        this.dateF = dateF;
    }

    public StatiqueCreneau(int numMatiere, int numGroupe, int numEns, int jourSemaine, Time heureSeance, Date dateD, Date dateF) {
        this.numSC = 1;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.numEns = numEns;
        this.jourSemaine = jourSemaine;
        this.heureSeance = heureSeance;
        this.dateD = dateD;
        this.dateF = dateF;
    }

    public int getNumSC() {
        return numSC;
    }

    public void setNumSC(int numSC) {
        this.numSC = numSC;
    }

    public int getNumMatiere() {
        return numMatiere;
    }

    public void setNumMatiere(int numMatiere) {
        this.numMatiere = numMatiere;
    }

    public int getNumGroupe() {
        return numGroupe;
    }

    public void setNumGroupe(int numGroupe) {
        this.numGroupe = numGroupe;
    }

    public int getNumEns() {
        return numEns;
    }

    public void setNumEns(int numEns) {
        this.numEns = numEns;
    }

    public int getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public Time getHeureSeance() {
        return heureSeance;
    }

    public void setHeureSeance(Time heureSeance) {
        this.heureSeance = heureSeance;
    }
    
    public Date getDateD(){
        return dateD;
    }
    
    public void setDateD(Date dateD) {
    
        this.dateD = dateD;
    }
    
    public Date getDateF(){
        return dateF;
    }
    
    public void setDateF(Date dateF) {
    
        this.dateF = dateF;
    }

   
}
