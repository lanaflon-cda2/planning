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
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private MonEmploiEnseignant monemp;
    private ArrayList listecrenovide;
    private ArrayList listepermut;
    private Seance seance;
    
    public void setMonEmp(MonEmploiEnseignant monemp) {
        this.monemp = monemp;
    }
    public void setAE(AcceuilEnseignant ae){
        this.ae = ae;
        this.ae.setSeanceRattrapage(this);
    }
    public void setnumseance(int i){

        this.numseance = i;
        if(this.numseance == 0) return;
        
        SeanceDAO seanceDAO = new SeanceDAO(con);
        seance = seanceDAO.find(this.numseance);
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
        int numEnsX;
        model = (DefaultTableModel) listeratt.getModel();
        model.setRowCount(0);
        Object[] objet;
        
        
        Absenter absenter = new Absenter(seance);
        ArrayList creno = absenter.getCreneauxMatchEnsGroupe();
        if(creno != null) {
            listecrenovide = new ArrayList();
            for(int i = 0; i < creno.size(); i++) {
                obj = creneauDAO.find((int) creno.get(i));
                listecrenovide.add(new Object[]{obj.getNumCreneau(), obj.getDateCreneau().toString(), obj.getHeureCreneau().toString()});
                objet = new Object[] {obj.getDateCreneau().toString() + " " + this.getDateName(obj.getDateCreneau()), obj.getHeureCreneau().toString(), ""};
                model.addRow(objet);
            }
            
        } else System.out.println(" Creno = " + creno + " Aucun creno vide trouvée.");
        
        //Supposons que creno = null. On va chercher si des permutations sont possibles; 
        System.out.println("\n\n");
        
        ArrayList permut = absenter.getPermutPossible();
        
        if(permut != null) {
            Permut x;
            listepermut = new ArrayList();
            for(int j = 0; j < permut.size(); j++) {

                x = (Permut) permut.get(j);
                numEnsX = x.getNumEns();
                ens = edao.find(numEnsX);
               
                ArrayList listCreneauEnsX = x.getCreneaux();
                for(int k = 0; k < listCreneauEnsX.size(); k++){
                    obj = creneauDAO.find((int) listCreneauEnsX.get(k));
                    listepermut.add(new Object[]{ens.getNumEns(), ens.getNomEns(), obj.getNumCreneau(), obj.getDateCreneau().toString(), obj.getHeureCreneau().toString()});
                    objet = new Object[] {obj.getDateCreneau().toString() + " " + this.getDateName(obj.getDateCreneau()), obj.getHeureCreneau().toString(), ens.getNomEns()};
                    model.addRow(objet);
                }

            }
        
        }
        
        listeratt.setModel(model);
    }
    
    public String getDateName(Date d){
        
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 140, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Veuillez selectionner un créneau convenable :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Séance à reporter");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        listeratt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DateSeance", "HeureSeance", "Enseignant"
            }
        ));
        jScrollPane1.setViewportView(listeratt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 430, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        try {
//            con.setAutoCommit(false);
//        } catch (Exception ex) {
//            System.out.println("Setting auto commit to false failed: " + ex);
//            return;
//        }
        
        
        Seance newseance1, newseance2, seanceoffert;
        SeanceDAO sd = new SeanceDAO(con);
        model = (DefaultTableModel) listeratt.getModel();
        Object[] obj;
        int row = listeratt.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Selectionnez un creneau valide!", "Seance de Rattrapages", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String dateSeanceNom = (String) model.getValueAt(row, 0);
        
        String dateSeance = dateSeanceNom.split(" ")[0];
        String heureSeance = (String) model.getValueAt(row, 1);
        String nomEns = (String) model.getValueAt(row, 2);
        if(!nomEns.equals("")) {
            for(int i = 0; i < listepermut.size(); i++){
               obj = (Object[]) listepermut.get(i);
               if(((String) obj[3]).equals(dateSeance) && ((String) obj[4]).equals(heureSeance) && ((String) obj[1]).equals(nomEns)){
                   sd.delete(seance);
                   seanceoffert = sd.getSeanceByFields((int)obj[2], (int)obj[0], seance.getNumGroupe(), 1);
                   sd.delete(seanceoffert);
                   newseance1 = new Seance(1000, (int)obj[2], seance.getNumEns(), seance.getNumMatiere(), seance.getNumGroupe(), 1);
                   sd.create(newseance1);
                   newseance2 = new Seance(1001, seance.getNumCreneau(), seanceoffert.getNumEns(), seanceoffert.getNumMatiere(), seanceoffert.getNumGroupe(), 1);
                   break;
               }
            }
        } else {
            for(int i = 0; i < listecrenovide.size(); i++) {
                obj = (Object[]) listecrenovide.get(i);
                if(((String) obj[1]).equals(dateSeance) && ((String) obj[2]).equals(heureSeance)){
                    sd.delete(seance);
                    newseance1 = new Seance(1002, (int)obj[0], seance.getNumEns(), seance.getNumMatiere(), seance.getNumGroupe(), 1);
                    sd.create(newseance1);
                    break;
                }
            }
        }
        
        this.ae.desktop.removeAll();
        this.ae.desktop.repaint();
        this.monemp.resetEMP();
        this.monemp.initEmp();
        this.ae.desktop.add(this.monemp);
        
        try {
            this.monemp.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.monemp.show();
    }//GEN-LAST:event_jButton1ActionPerformed


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
