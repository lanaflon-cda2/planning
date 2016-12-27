/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.view.Enseignant;

import com.planning.controler.Absenter;
import com.planning.controler.Permut;
import com.planning.dao.implement.CreneauDAO;
import com.planning.dao.implement.EnseignantDAO;
import com.planning.dao.implement.SeanceDAO;
import com.planning.model.ConnexionBD;
import com.planning.model.Creneau;
import com.planning.model.Enseignant;
import com.planning.model.Seance;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Azough Mehdi
 */
public class SeanceRattrapage extends javax.swing.JInternalFrame {

    /**
     * Creates new form SeanceRattrapage
     */
    private int numseance;
    Connection con=ConnexionBD.init();
     DefaultTableModel model;
    
    public void setnumseance(int i){
        this.numseance = i;
        SeanceDAO seanceDAO = new SeanceDAO(con);
        Seance seance = seanceDAO.find(this.numseance);
        this.getRattrapage(seance);
    }
    
    public void getRattrapage(Seance seance){
        CreneauDAO creneauDAO = new CreneauDAO(con);
        Creneau obj;
        EnseignantDAO edao = new EnseignantDAO(con);
        Enseignant ens;
        model = (DefaultTableModel) listeratt.getModel();
        Object[] objet;
        
        
        Absenter absenter = new Absenter(seance);
        ArrayList creno = absenter.getCreneauxMatchEnsGroupe();
        if(creno != null) {
            System.out.println("Les creneaux trouvés sont: ");
            for(int i = 0; i < creno.size(); i++) {
                obj = creneauDAO.find((int) creno.get(i));
                System.out.println("numCreneau " + obj.getNumCreneau() + " " + this.getDateName(obj.getDateCreneau())+ " " + obj.getDateCreneau() + " " + obj.getHeureCreneau());
                objet = new Object[] {obj.getDateCreneau().toString() + " " + this.getDateName(obj.getDateCreneau()), obj.getHeureCreneau(), ""};
                model.addRow(objet);
            }
            
        } else System.out.println(" Creno = " + creno + " Aucun creno vide trouvée.");
        
        //Supposons que creno = null. On va chercher si des permutations sont possibles; 
        System.out.println("\n\n");
        
        ArrayList permut = absenter.getPermutPossible();
        
        if(permut != null) {
            Permut x;
             
            for(int j = 0; j < permut.size(); j++) {

                x = (Permut) permut.get(j);
                int numEnsX = x.getNumEns();
                ens = edao.find(numEnsX);
                System.out.println("Le professeur de numero " + numEnsX + " et de nom " + ens.getNomEns() + "peut offrir les créneaux suivants: ");
                ArrayList listCreneauEnsX = x.getCreneaux();
                for(int k = 0; k < listCreneauEnsX.size(); k++){
                    obj = creneauDAO.find((int) listCreneauEnsX.get(k));
                    System.out.println("numCreneau " + obj.getNumCreneau() + " " + this.getDateName(obj.getDateCreneau())+ " " + obj.getDateCreneau() + " " + obj.getHeureCreneau());
                    objet = new Object[] {obj.getDateCreneau().toString() + " " + this.getDateName(obj.getDateCreneau()), obj.getHeureCreneau(), ens.getNumEns()};
                    model.addRow(objet);
                }

            }
        
        } 
    }
    
    public static String getDateName(Date d){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //System.out.print("Today  is");
        switch (day) {
            case 1:
                return "Dimanche";
            case 2:
                return "Lundi";
            case 3:
                return "Mardi";
            case 4:
                return "Mercredi";
            case 5:
                return "Jeudi";
            case 6:
                return "Vendredi";
            default:
                return "Samedi";
        }
    }
    public SeanceRattrapage(){
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeratt = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("seances rattrapage");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Matière");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, -1, -1));

        jLabel4.setText("Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel5.setText("Heure");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 210, 120));

        jButton1.setText("Confirmer");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 140, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Veuillez selectionner un créneau convenable :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Séance à reporté ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        listeratt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DateSeance", "HeureSeance", "Enseignant"
            }
        ));
        jScrollPane1.setViewportView(listeratt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 310, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listeratt;
    // End of variables declaration//GEN-END:variables
}
