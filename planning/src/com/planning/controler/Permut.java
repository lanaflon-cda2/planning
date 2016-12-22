/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;

import java.util.ArrayList;

/**
 *
 * @author genereux
 */
public class Permut {
    
    private int numEns;

    private ArrayList creneaux;
    
    public Permut(){
        this.numEns = 0;
        this.creneaux = new ArrayList();
        
    }

    public Permut (int numEns, ArrayList creneaux) {

        this.numEns = numEns;
        this.creneaux = creneaux;
    }

    public void setNumEns (int numEns){
        this.numEns = numEns;
    }

    public void setCrenaux (ArrayList creneaux){
        this.creneaux = creneaux;
    }
    
    public int getNumEns(){
       return numEns;
    }
    
    public ArrayList getCreneaux(){
    
       return creneaux;
    }
    
    public void addCreneaux(int numCreneau){
        creneaux.add(numCreneau);
    }
    
    public void removeCreneaux(int index){
        creneaux.remove(index);
    }
}
