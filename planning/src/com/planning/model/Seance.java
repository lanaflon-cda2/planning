package com.planning.model;

public class Seance {

    private int numSeance;
    private int numCreneau;
    private int etatSeance;
    private int numEns;
    private int numGroupe;
    private int numMatiere;

    public Seance(int numSeance,int numCreneau ,int numEns, int numMatiere, int numGroupe, int etatSeance) {
        
        this.numSeance = numSeance;
        this.numCreneau = numCreneau;
        this.numEns = numEns;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.etatSeance = etatSeance;
    }
    
    public Seance(int numSeance,int numCreneau ,int numEns, int numMatiere, int numGroupe) {
        
        this.numSeance = numSeance;
        this.numCreneau = numCreneau;
        this.numEns = numEns;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.etatSeance = 1;
    }
    
    public Seance(int numCreneau ,int numEns, int numMatiere, int numGroupe) {
        
        this.numSeance = 1;
        this.numCreneau = numCreneau;
        this.numEns = numEns;
        this.numMatiere = numMatiere;
        this.numGroupe = numGroupe;
        this.etatSeance = -1;
    }
    
    public Seance(){}

    public Seance(int numSeance) {
        this.numSeance = numSeance;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }
    
    public int getNumCreneau() {
        return numCreneau;
    }

    public void setNumCreneau(int numCreneau) {
        this.numCreneau = numCreneau;
    }
    
    public int getEtatSeance() {
        return etatSeance;
    }

    public void setEtatSeance(int etatSeance) {
        this.etatSeance = etatSeance;
    }

    public int getNumEns() {
        return numEns;
    }

    public void setNumEns(int numEns) {
        this.numEns = numEns;
    }

    public int getNumGroupe() {
        return numGroupe;
    }

    public void setNumGroupe(int numGroupe) {
        this.numGroupe = numGroupe;
    }

    public int getNumMatiere() {
        return numMatiere;
    }

    public void setNumMatiere(int numMatiere) {
        this.numMatiere = numMatiere;
    }

    
    public String toString() {
        return "com.planning.model.Seance[ numSeance=" + numSeance + " ]";
    }
    
}
