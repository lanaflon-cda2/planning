package com.planning.model;

public class Users {

    private String iDUser;
    private String motDePasse;
    private Enseignant enseignant = new Enseignant();

    public Users() {
    }

    public Users(String iDUser) {
        this.iDUser = iDUser;
    }

    public Users(String iDUser, String motDePasse) {
       this.iDUser = iDUser;
       this.motDePasse= motDePasse;
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

    public void setEnseignant(Enseignant ensaignant){
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

