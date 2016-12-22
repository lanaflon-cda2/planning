package com.planning.model;

import java.util.HashSet;
import java.util.Set;

public class Matiere {

    private int numMatiere;
    private String nomMatiere;
    private Set<Seance> seanceList = new HashSet<> ();

    public Matiere() {
    }

    public Matiere(int numMatiere) {
        this.numMatiere = numMatiere;
    }
    
    public Matiere(int numMatiere, String nomMatiere) {
        this.numMatiere = numMatiere;
        this.nomMatiere = nomMatiere;
    }

    public int getNumMatiere() {
        return numMatiere;
    }

    public void setNumMatiere(int numMatiere) {
        this.numMatiere = numMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
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
    
    public boolean equals(Matiere matiere){
        return this.getNumMatiere() == matiere.getNumMatiere();
    }

    public String toString() {
        return "com.planning.model.Matiere[ numMatiere=" + numMatiere + " ]";
    }
    
}
