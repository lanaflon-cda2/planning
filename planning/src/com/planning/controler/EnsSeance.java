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
public class EnsSeance {
    
    private int numEns;

    private ArrayList seanceEns;

    public EnsSeance (int numEns, ArrayList seanceEns) {

        this.numEns = numEns;
        this.seanceEns = seanceEns;
    }

    public void setNumEns (int numEns){
        this.numEns = numEns;
    }

    public void setSeanceEns (ArrayList seanceEns){
        this.seanceEns = seanceEns;
    }
    
    public int getNumEns(){
       return numEns;
    }
}
