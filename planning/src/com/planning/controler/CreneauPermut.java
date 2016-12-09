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
public class CreneauPermut {
    
    private int numEns;

    private ArrayList creneaux;
    
    public CreneauPermut(){}

    public CreneauPermut (int numEns, ArrayList creneaux) {

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
