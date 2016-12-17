/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;

import com.planning.dao.implement.CreneauDAO;
import com.planning.dao.implement.GroupeDAO;
import com.planning.dao.implement.MatiereDAO;
import com.planning.dao.implement.SeanceDAO;
import com.planning.model.ConnexionBD;
import com.planning.model.Creneau;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Azough Mehdi
 */
public class ConsulterEMP {
    
    Connection conn = ConnexionBD.init();
    public String[][] getNomGrpMat(int numEns, Date dd, Date df) {
        String listNomGrpMat[][] = new String[20][4];
        String tab[] = new String[4];
        SeanceDAO sd = new SeanceDAO(conn);
        Set<Seance> listSeance = sd.findByNumEns(numEns);
        
        Iterator it = listSeance.iterator();
        Seance s = new Seance();
        int numCreneau;
        Creneau cren = new Creneau();
        CreneauDAO crend = new CreneauDAO(conn);
        while(it.hasNext()){
             s = (Seance) it.next();
             cren = crend.find(s.getNumCreneau());
             
             if((dd.compareTo(cren.getDateCreneau()) > 0) && (df.compareTo(cren.getDateCreneau())) > 0 ){
                 
                 listSeance.remove(s);
             }
             
        }
        
        it = listSeance.iterator();
        int numMat, numGroupe;
        MatiereDAO matd = new MatiereDAO(conn);
        GroupeDAO grpd = new GroupeDAO(conn);
        String nomMat, nomGroupe;
        int i = 0;
        while(it.hasNext()){
            s = (Seance) it.next();
            
            nomMat = (matd.find(s.getNumMatiere())).getNomMatiere();
            nomGroupe = (grpd.find(s.getNumGroupe())).getNomGroupe();
            cren = crend.find(s.getNumCreneau());
            tab[0] = nomMat;
            tab[1] = nomGroupe;
            
            listNomGrpMat[i] = tab;
            i++;
            
        }
        
        
        return listNomGrpMat;
    }
}
