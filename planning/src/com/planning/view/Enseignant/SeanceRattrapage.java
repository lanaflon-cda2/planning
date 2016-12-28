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
import javax.swing.JOptionPane;
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
    private Connection con=ConnexionBD.init();
    private DefaultTableModel model;
    private AcceuilEnseignant ae;
    
    public void setAE(AcceuilEnseignant ae){
        this.ae = ae;
        this.ae.setSeanceRattrapage(this);
    }
    public void setnumseance(int i){
        this.numseance = i;
        SeanceDAO seanceDAO = new SeanceDAO(con);
        Seance seance = seanceDAO.find(this.numseance);
        //this.getRattrapage(seance);
        this.getRattrapage(seance);
    }
    
    public int getNumSeance(){
        return this.numseance;
    }
    public void getRattrapage(Seance seance){
        CreneauDAO creneauDAO = new CreneauDAO(con);
        Creneau obj;
        EnseignantDAO edao = new EnseignantDAO(con);
        Enseignant ens;
        model = (DefaultTableModel) listeratt.getModel();
        model.setRowCount(0);
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
                    objet = new Object[] {obj.getDateCreneau().toString() + " " + this.getDateName(obj.getDateCreneau()), obj.getHeureCreneau(), ens.getNomEns()};
                    model.addRow(objet);
                }

            }
        
        }
        
        listeratt.setModel(model);
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
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void init() {
        
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeratt = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("seances rattrapage");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Matière");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, -1, -1));

        jLabel4.setText("Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel5.setText("Heure");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, -1));

        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 130, 20));

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 130, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 210, 120));

        jButton1.setText("Confirmer");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 140, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Veuillez selectionner un créneau convenable :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Séance à reporter");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_clicked_confirmer(evt);
            }
        });

        listeratt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DateSeance", "HeureSeance", "Enseignant"
            }
        ));
        jScrollPane1.setViewportView(listeratt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 430, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list_clicked_confirmer(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_clicked_confirmer
        // TODO add your handling code here:
        jButton1.setEnabled(true);
    }//GEN-LAST:event_list_clicked_confirmer

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        listeratt.clearSelection();
        jButton1.setEnabled(false);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable listeratt;
    // End of variables declaration//GEN-END:variables
}
