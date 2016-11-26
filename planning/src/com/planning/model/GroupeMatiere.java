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
    private Date dateDebut;
    private Date dateFin;

    
   public GroupeMatiere(int numMatiere, int numGroupe, Date dateDebut, Date dateFin){
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.dateDebut = dateDebut;
        this.dateFin= dateFin;
   
   }
  
    public GroupeMatiere(int numMatiere, int numGroupe){
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
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
