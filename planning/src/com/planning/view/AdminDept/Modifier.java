/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.view.AdminDept;

import com.planning.dao.implement.FiliereDAO;
import com.planning.dao.implement.GroupeDAO;
import com.planning.dao.implement.MatiereDAO;
import com.planning.model.ConnexionBD;
import com.planning.model.Filiere;
import com.planning.model.Groupe;
import com.planning.model.Matiere;
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Azough Mehdi
 */
public class Modifier extends javax.swing.JFrame {
    Groupe groupe;
    GroupeDAO groupeD;
    FiliereDAO filD;
    ArrayList  listefil;
    Filiere fil;
    Connection conn = ConnexionBD.init();
    GererGroupe gererGroupe;
    int oldNumGroupe;
    
    
    public Modifier() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        filD = new FiliereDAO(conn);
        listefil=filD.findAll();
        for (int i = 0; i < listefil.size(); i++) {
            
            fil = (Filiere)listefil.get(i);
            filierecombo.addItem(fil.getNomFiliere());
            
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        indication = new javax.swing.JLabel();
        Sauvegarder = new javax.swing.JButton();
        nomgroupefield = new javax.swing.JTextField();
        filierecombo = new javax.swing.JComboBox<>();
        niveaucombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajouter ");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        indication.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        indication.setText("Veuillez modifier les informations suivantes :");
        getContentPane().add(indication, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        Sauvegarder.setText("Enregistrer");
        Sauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SauvegarderActionPerformed(evt);
            }
        });
        getContentPane().add(Sauvegarder, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 120, 30));
        getContentPane().add(nomgroupefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 190, 30));

        getContentPane().add(filierecombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 190, 30));

        niveaucombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        getContentPane().add(niveaucombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 50, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nom du groupe * ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Filière *");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 107, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Niveau *");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 157, -1, 30));

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void setGererGroupe(GererGroupe gg) {
        this.gererGroupe = gg;
    }
    
    public void setOldNumGroupe(int x) {
        this.oldNumGroupe = x;
    }
    private void SauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SauvegarderActionPerformed
       
        String nomgroupe = nomgroupefield.getText();
        if(!nomgroupe.equals("")) {
            int numFiliere = 0;
            String fils = (String) filierecombo.getSelectedItem();
            for (int i = 0; i < listefil.size(); i++) {
                fil = (Filiere) listefil.get(i);
                if(fils.equals(fil.getNomFiliere())) {
                    numFiliere = fil.getNumFiliere();
                    break;
                }
            }
            int niveau = niveaucombo.getSelectedIndex() + 1;
            groupe = new Groupe(1, numFiliere, nomgroupe, niveau);
            groupeD = new GroupeDAO(conn);
            groupeD.delete(new Groupe(this.oldNumGroupe));
            groupeD.create(groupe);
            this.gererGroupe.affichage();
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null,"Le champ Nom de Matière est obligatoire!","Formulaire incomplet!", JOptionPane.QUESTION_MESSAGE, null);
        }
    
    }//GEN-LAST:event_SauvegarderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void setNomField(String s) {
        this.nomgroupefield.setText(s);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modifier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sauvegarder;
    private javax.swing.JComboBox<String> filierecombo;
    public javax.swing.JLabel indication;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> niveaucombo;
    public javax.swing.JTextField nomgroupefield;
    // End of variables declaration//GEN-END:variables
}
