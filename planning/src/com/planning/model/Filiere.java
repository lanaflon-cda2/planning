package com.planning.model;

public class Filiere{

    private int numFiliere;
    private String nomFiliere;

    public Filiere() {
    }

    public Filiere(int numFiliere) {
        this.numFiliere = numFiliere;
    }

    public Filiere(int numFiliere, String nomFiliere) {
        this.nomFiliere = nomFiliere;
        this.numFiliere = numFiliere;
    }

    public int getNumFiliere() {
        return numFiliere;
    }

    public void setNumFiliere(int numFiliere) {
        this.numFiliere = numFiliere;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }
    
    public boolean equals(Filiere filiere){
        return this.getNumFiliere() == filiere.getNumFiliere();
    }

    @Override
    public String toString() {
        return "com.planning.model.Filiere[ numFiliere=" + numFiliere + " ]";
    }
    
}
