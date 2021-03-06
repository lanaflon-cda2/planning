/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.view.Enseignant;

import com.planning.view.AdminDept.AcceuilAdminDept;
import com.planning.view.Deconnexion;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azough Mehdi
 */
public class AcceuilEnseignant extends javax.swing.JFrame {
       
    /**
     * Creates new form Acceuil
     */
    SeanceRattrapage sr;
    
    public void setSeanceRattrapage(SeanceRattrapage sr){
        this.sr = sr;
    }
    
    String idUser;
    
    MonEmploiEnseignant monEmploiEnseignant1 = new MonEmploiEnseignant();
    
    MonCompteEnseignant me = new MonCompteEnseignant();
    
    Modifier modifier = new Modifier();
    
    public void setME(MonCompteEnseignant me){
        this.me = me;
        me.setIDUserMCE(this.idUser);
    }
    
    
    
    public void setidUser(String s){
        this.idUser = s;
        monEmploiEnseignant1.setIDUserMEE(this.idUser); 
        monEmploiEnseignant1.initEmp();
        monEmploiEnseignant1.setAE(this);
        modifier.setIDUserMod(this.idUser);
    }
       
    public AcceuilEnseignant() {
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

        jButton1 = new javax.swing.JButton();
        desktop = new javax.swing.JDesktopPane();
        moncompte = new javax.swing.JButton();
        monemploi = new javax.swing.JButton();
        seanceratt = new javax.swing.JButton();
        photo = new javax.swing.JLabel();
        acceuilBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Deconnexion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, -1, 30));

        com.planning.view.Enseignant.MonCompteEnseignant mce = new com.planning.view.Enseignant.MonCompteEnseignant();
        this.desktop.add(mce);
        try {
            mce.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        mce.show();
        getContentPane().add(desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 860, 570));

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
        getContentPane().add(moncompte, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 110, 30));

        monemploi.setBackground(new java.awt.Color(204, 204, 204));
        monemploi.setFont(new java.awt.Font("Champagne & Limousines", 1, 14)); // NOI18N
        monemploi.setText("Emploi du temps");
        monemploi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monemploiMouseClicked(evt);
            }
        });
        monemploi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monemploiActionPerformed(evt);
            }
        });
        getContentPane().add(monemploi, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 130, 160, 90));

        seanceratt.setBackground(new java.awt.Color(204, 204, 204));
        seanceratt.setFont(new java.awt.Font("Champagne & Limousines", 1, 14)); // NOI18N
        seanceratt.setText("Rattrapages");
        seanceratt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seancerattMouseClicked(evt);
            }
        });
        seanceratt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seancerattActionPerformed(evt);
            }
        });
        getContentPane().add(seanceratt, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 210, 160, 100));

        photo.setForeground(new java.awt.Color(255, 255, 255));
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setText("photo");
        getContentPane().add(photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 90, 100));

        acceuilBG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acceuilBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("AcceuilBG.png")));
        acceuilBG.setToolTipText("");
        getContentPane().add(acceuilBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void moncompteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moncompteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_moncompteMouseExited

    private void moncompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moncompteActionPerformed
        monemploi.setBackground(new java.awt.Color(240, 240, 240));
        seanceratt.setBackground(new java.awt.Color(240, 240, 240));
                
        this.desktop.removeAll();
        this.desktop.repaint();
        
        this.desktop.add(me);
    
        
        try {
            me.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        me.show();
        monEmploiEnseignant1.setAE(this);
       
    }//GEN-LAST:event_moncompteActionPerformed

    private void monemploiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monemploiMouseClicked
       
       
    }//GEN-LAST:event_monemploiMouseClicked

    private void moncompteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moncompteMouseClicked
        
    }//GEN-LAST:event_moncompteMouseClicked

    private void seancerattMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seancerattMouseClicked
        
    }//GEN-LAST:event_seancerattMouseClicked

    private void monemploiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monemploiActionPerformed
       monemploi.setBackground(java.awt.SystemColor.activeCaption);
       seanceratt.setBackground(new java.awt.Color(240, 240, 240));
        
       this.desktop.removeAll();
       this.desktop.repaint(); 
       monEmploiEnseignant1.setTitle("Emploi du temps");
       this.desktop.add(monEmploiEnseignant1);
        try {
            monEmploiEnseignant1.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        monEmploiEnseignant1.show();
        monEmploiEnseignant1.setAE(this);
    }//GEN-LAST:event_monemploiActionPerformed

    private void seancerattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seancerattActionPerformed
        
        seanceratt.setBackground(java.awt.SystemColor.activeCaption);
        monemploi.setBackground(new java.awt.Color(240, 240, 240));
        this.desktop.removeAll();
        this.desktop.repaint();
        if(sr == null) sr = new SeanceRattrapage();
        sr.setTitle("Seances de Rattrapages");
        this.desktop.add(this.sr);
        try {
            sr.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AcceuilAdminDept.class.getName()).log(Level.SEVERE, null, ex);
        }
        sr.show();
        monEmploiEnseignant1.setAE(this);
    }//GEN-LAST:event_seancerattActionPerformed

    private void jLabel1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {                                               
        
    // TODO add your handling code here:
    }                                              

    private void jLabel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel1ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1ComponentResized

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         this.dispose();
         new Deconnexion().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AcceuilEnseignant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcceuilEnseignant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcceuilEnseignant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcceuilEnseignant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcceuilEnseignant().setVisible(true);
                
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acceuilBG;
    public javax.swing.JDesktopPane desktop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton moncompte;
    private javax.swing.JButton monemploi;
    private javax.swing.JLabel photo;
    private javax.swing.JButton seanceratt;
    // End of variables declaration//GEN-END:variables
}
