/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;

import com.planning.dao.implement.CreneauDAO;
import com.planning.dao.implement.FiliereDAO;
import com.planning.dao.implement.GroupeDAO;
import com.planning.dao.implement.MatiereDAO;
import com.planning.dao.implement.SeanceDAO;
import com.planning.model.ConnexionBD;
import com.planning.model.Creneau;
import com.planning.model.Filiere;
import com.planning.model.Groupe;
import com.planning.model.Matiere;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Azough Mehdi
 */
public class ConsulterEMP {
    
    Connection conn = ConnexionBD.init();
    
    public Date setDate(Date date) {
        
        long d = date.getTime();
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        d = cal.getTimeInMillis();
        return new Date(d);
    }
    
    public ArrayList getGrpMat(int numEns, Date dd, Date df) {
        
        dd = this.setDate(dd);
        df = this.setDate(df);
        ArrayList listGrpMat = new ArrayList();
        String tab[] = new String[6];
        SeanceDAO sd = new SeanceDAO(conn);
        Set<Seance> listSeance = new HashSet<>();
        Set<Seance> listSeance1 = sd.findByNumEns(numEns);
        Date dateCren;
        
        Iterator it = listSeance1.iterator();
        Seance s;
        Creneau cren;
        CreneauDAO crend = new CreneauDAO(conn);
        
        while(it.hasNext()){
            s = (Seance) it.next();
            cren = crend.find(s.getNumCreneau());

            dateCren = cren.getDateCreneau();
             if((dateCren.compareTo(dd) >= 0) && (dateCren.compareTo(df) <= 0) ){
                 listSeance.add(s);
             }
             
        }
        
        it = listSeance.iterator();
        MatiereDAO matd = new MatiereDAO(conn);
        GroupeDAO grpd = new GroupeDAO(conn);
        FiliereDAO fild = new FiliereDAO(conn);
        Groupe grp;
        Matiere mat;
        Filiere fil;
        int i = 0;
        while(it.hasNext()){
            s = (Seance) it.next();
            mat = matd.find(s.getNumMatiere());
            grp = grpd.find(s.getNumGroupe());
            fil = fild.find(grp.getNumFiliere());
            cren = crend.find(s.getNumCreneau());
            dateCren = cren.getDateCreneau();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateCren);
            
            tab[0] = mat.getNomMatiere();
            tab[1] = grp.getNomGroupe();
            tab[2] = fil.getNomFiliere();
            tab[3] = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));        
            tab[4] = cren.getHeureCreneau().toString();
            tab[5] = dateCren.toString();
            
            System.out.println("numero " + ++i + " " + tab[0]+ " " + tab[1]+ " " + tab[2]+ " " + tab[3]+ " " + tab[4]);
            listGrpMat.add(tab);       
        }
        return listGrpMat;
    }
}
