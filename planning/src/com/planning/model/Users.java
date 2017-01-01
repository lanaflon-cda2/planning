package com.planning.model;

public class Users {

    private String iDUser;
    private String motDePasse;
    private String fonction;
    private int numFiliere;
    private String nomFiliere;
    
    public void setNumFiliere(int x) {
        this.numFiliere =x;
    }
    
    public int getNumFiliere() {
        return this.numFiliere;
    }
    
    public void setNomFiliere(String s) {
        this.nomFiliere = s;
    }
    
    public String getNomFiliere() {
        return this.nomFiliere;
    }
    private Enseignant enseignant;

    public Users() {
    }

    public Users(String iDUser) {
        this.iDUser = iDUser;
        this.motDePasse = "";
        this.fonction = "";
        this.numFiliere = 0;
    }

    public Users(String iDUser, String motDePasse) {
       this.iDUser = iDUser;
       this.motDePasse= motDePasse;
        this.fonction = "";
        this.numFiliere = 0;
    }

    public String getIDUser() {
        return iDUser;
    }

    public void setIDUser(String iDUser) {
        this.iDUser = iDUser;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Users(String iDUser, String motDePasse, String fonction) {
        this.iDUser = iDUser;
        this.motDePasse = motDePasse;
        this.fonction = fonction;
        this.numFiliere = 0;
    }
    
    public Users(String iDUser, String motDePasse, String fonction, int numFiliere) {
        this.iDUser = iDUser;
        this.motDePasse = motDePasse;
        this.fonction = fonction;
        this.numFiliere = numFiliere;
    }
    
    public void setFonction(String f) {
        this.fonction = f;
    }
    
    public String getFonction() {
        return this.fonction;
    }
    
    public void setEnseignant(Enseignant enseignant){
        this.enseignant = enseignant;
    }
    
    public Enseignant getEnseignant(){
        return this.enseignant;
    }
    
        
    public boolean equals(Users user){
        return this.getIDUser() == user.getIDUser();
    }


    public String toString() {
        return "com.planning.model.Users[ iDUser=" + iDUser + " ]";
    }
    
}

