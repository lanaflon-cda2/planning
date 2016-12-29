package com.planning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Enseignant implements Serializable {

    private static final long serialVersionUID = 1L;
    private int numEns;
    private String nomEns;
    private String prenomEns;
    private String mail;
    private Long tel;
    private String iDUser;
    
    private Set<Seance> seanceList = new HashSet<>();

    public Enseignant() {
    }

    public Enseignant(int numEns) {
        this.numEns = numEns;
    }
    public Enseignant(String nomEns, String prenomEns, String mail, Long tel, String iDUser) {
        this.nomEns = nomEns;
        this.prenomEns = prenomEns;
        this.mail = mail;
        this.tel = tel;
        this.iDUser = iDUser;
    
    }
    public Enseignant(int numEns, String nomEns, String prenomEns, String mail, Long tel, String iDUser) {
        this.numEns = numEns;
        this.nomEns = nomEns;
        this.prenomEns = prenomEns;
        this.mail = mail;
        this.tel = tel;
        this.iDUser = iDUser;
    }
    
    public Enseignant(String id){
        this.iDUser = id;
    }

    public int getNumEns() {
        return numEns;
    }

    public void setNumEns(int numEns) {
        this.numEns = numEns;
    }

    public String getNomEns() {
        return nomEns;
    }

    public void setNomEns(String nomEns) {
        this.nomEns = nomEns;
    }

    public String getPrenomEns() {
        return prenomEns;
    }

    public void setPrenomEns(String prenomEns) {
        this.prenomEns = prenomEns;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getIDUser() {
        return iDUser;
    }

    public void setIDUser(String iDUser) {
        this.iDUser = iDUser;
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
    
    public boolean equals(Enseignant enseignant){
        return this.getNumEns() == enseignant.getNumEns();
    }

    
    public String toString() {
        return "com.planning.model.Enseignant[ numEns=" + numEns + " ]";
    }
    
}
