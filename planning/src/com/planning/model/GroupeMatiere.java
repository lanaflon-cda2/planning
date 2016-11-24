/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.model;

import java.sql.Date;

/**
 *
 * @author Oumaima
 */
public class GroupeMatiere {       
    private int numGroupe;
    private int numMatiere;
    private int numFiliere;
    private Date dateDebut;
    private Date dateFin;

    
   public GroupeMatiere(int numMatiere, int numGroupe ,int numFiliere, Date dateDebut, Date dateFin){
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.numFiliere = numFiliere;
        this.dateDebut = dateDebut;
        this.dateFin= dateFin;
   
   }
  
    public GroupeMatiere(int numMatiere, int numGroupe, int numFiliere){
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.numFiliere = numFiliere;
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
    
    public int getnumFiliere() {
        return numFiliere;
    }

    public void setnumFiliere(int numFiliere) {
        this.numFiliere = numFiliere;
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
