/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Oumaima
 */
public class GroupeMatiere {
        
    
    private int numGM;
    private int numGroupe;
    private int numMatiere;
    private Date dateDebut;
    private Date dateFin;
    
   public GroupeMatiere(int numGM, int numGroupe , int numMatiere , Date dateDebut, Date dateFin){
        this.numGM = numGM;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.dateDebut = dateDebut;
        this.dateFin= dateFin;
   
   }
  
    public GroupeMatiere(){}

    public GroupeMatiere(int numGM) {
        this.numGM = numGM;
    }

    public int getNumGM() {
        return numGM;
    }   
    
    public void setNumGM(int numGM) {
        this.numGM = numGM;
    }
    
    public int getnumGroupe() {
        return numGroupe;
    }

    public void setnumGroupe(int numGroupe) {
        this.numGroupe = numGroupe;
    }

    public int getnumMatiere() {
        return numMatiere;
    }

    public void setnumMatiere(int numMatiere) {
        this.numMatiere = numMatiere;
    }    
    
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    } 
    
    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }    
    
}
