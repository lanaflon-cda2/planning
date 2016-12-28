/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.view.AdminSyst;

import com.planning.view.Deconnexion;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azough Mehdi
 */
public class AcceuilAdminSyst extends javax.swing.JFrame {

    /**
     * Creates new form Acceuil
     */
    public AcceuilAdminSyst() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        moncompte = new javax.swing.JButton();
        utilisateurs = new javax.swing.JButton();
        photo = new javax.swing.JLabel();
        deconnexion = new javax.swing.JLabel();
        acceuilBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        com.planning.view.AdminSyst.MonCompteSyst mcs = new com.planning.view.AdminSyst.MonCompteSyst();
        this.desktop.add(mcs);
        try {
            mcs.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilAdminSyst.class.getName()).log(Level.SEVERE, null, ex);
        }
        mcs.show();
        getContentPane().add(desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 810, 550));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("AdminSyst");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        moncompte.setBackground(new java.awt.Color(255, 255, 255));
        moncompte.setFont(new java.awt.Font("Champagne & Limousines", 1, 14)); // NOI18N
        moncompte.setText("Mon Compte");
        moncompte.setBorderPainted(false);
        moncompte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moncompteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                moncompteMouseExited(evt);
            }
        });
        moncompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moncompteActionPerformed(evt);
            }
        });
        getContentPane().add(moncompte, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 110, 30));

        utilisateurs.setBackground(new java.awt.Color(204, 204, 204));
        utilisateurs.setFont(new java.awt.Font("Champagne & Limousines", 1, 14)); // NOI18N
        utilisateurs.setText("Utilisateurs");
        utilisateurs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                utilisateursMouseClicked(evt);
            }
        });
        utilisateurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilisateursActionPerformed(evt);
            }
        });
        getContentPane().add(utilisateurs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 190, 110));

        photo.setForeground(new java.awt.Color(255, 255, 255));
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setText("photo");
        getContentPane().add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 90, 110));

        deconnexion.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        deconnexion.setForeground(new java.awt.Color(255, 255, 255));
        deconnexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deconnexion.setText("Deconnexion");
        deconnexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnexionMouseClicked(evt);
            }
        });
        getContentPane().add(deconnexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 100, -1));

        acceuilBG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acceuilBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/planning/images/AcceuilBG.png"))); // NOI18N
        getContentPane().add(acceuilBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void utilisateursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_utilisateursMouseClicked
       this.desktop.removeAll();
       this.desktop.repaint();  
       GererUtilisateurs gu = new GererUtilisateurs();
       this.desktop.add(gu);
        try {
            gu.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilAdminSyst.class.getName()).log(Level.SEVERE, null, ex);
        }
        gu.show();
       
    }//GEN-LAST:event_utilisateursMouseClicked

    private void deconnexionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnexionMouseClicked
        dispose();
        new Deconnexion().setVisible(true);
    }//GEN-LAST:event_deconnexionMouseClicked

    private void utilisateursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilisateursActionPerformed
        GererUtilisateurs gu = new GererUtilisateurs();
        gu.UpdateTable();
        utilisateurs.setBackground(java.awt.SystemColor.activeCaption);
        
        
    }//GEN-LAST:event_utilisateursActionPerformed

    private void moncompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moncompteActionPerformed
      utilisateurs.setBackground(new java.awt.Color(240, 240, 240));
       this.desktop.removeAll();
        this.desktop.repaint();
        MonCompteSyst mcs = new MonCompteSyst();
        this.desktop.add(mcs);
        try {
            mcs.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilAdminSyst.class.getName()).log(Level.SEVERE, null, ex);
        }
        mcs.show();
    }//GEN-LAST:event_moncompteActionPerformed

    private void moncompteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moncompteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_moncompteMouseExited

    private void moncompteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moncompteMouseClicked
       
    }//GEN-LAST:event_moncompteMouseClicked

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
            java.util.logging.Logger.getLogger(AcceuilAdminSyst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcceuilAdminSyst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcceuilAdminSyst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcceuilAdminSyst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcceuilAdminSyst().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acceuilBG;
    private javax.swing.JLabel deconnexion;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton moncompte;
    private javax.swing.JLabel photo;
    private javax.swing.JButton utilisateurs;
    // End of variables declaration//GEN-END:variables
}
