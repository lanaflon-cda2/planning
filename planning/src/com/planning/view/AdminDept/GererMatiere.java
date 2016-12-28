/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.view.AdminDept;

import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author Azough Mehdi
 */
public class GererMatiere extends javax.swing.JInternalFrame {

    /**
     * Creates new form GererEmloi
     */
    public GererMatiere() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.getContentPane().setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ajoutermat = new javax.swing.JRadioButton();
        nommat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ajouter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listemat = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        modifiermat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        confirmer = new javax.swing.JButton();

        setTitle("Gérer matières");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Modifier une matière :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        ajoutermat.setText("Ajouter une matière");
        ajoutermat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutermatActionPerformed(evt);
            }
        });
        getContentPane().add(ajoutermat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        nommat.setEnabled(false);
        nommat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nommatActionPerformed(evt);
            }
        });
        getContentPane().add(nommat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 180, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nom matière");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        ajouter.setText("Ajouter");
        ajouter.setEnabled(false);
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });
        getContentPane().add(ajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 110, 30));

        listemat.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "test1", "test2", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listemat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listemat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listematMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listemat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 140, 200));

        jLabel3.setText("Liste des matières :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        modifiermat.setEnabled(false);
        getContentPane().add(modifiermat, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 210, 30));

        jLabel4.setText("Modification :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, -1));

        confirmer.setText("Confirmer");
        confirmer.setEnabled(false);
        getContentPane().add(confirmer, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 120, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nommatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nommatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nommatActionPerformed

    private void ajoutermatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutermatActionPerformed
        if(ajoutermat.isSelected()){
            ajouter.setEnabled(true);
            nommat.setEnabled(true);
        }
        else{
            ajouter.setEnabled(false);
            nommat.setEnabled(false);
        }
    }//GEN-LAST:event_ajoutermatActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        
           
    }//GEN-LAST:event_ajouterActionPerformed

    private void listematMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listematMouseClicked
        modifiermat.setEnabled(true);
        modifiermat.setText("");
        modifiermat.setText(listemat.getSelectedValue());
        confirmer.setEnabled(true);
        
    }//GEN-LAST:event_listematMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        confirmer.setEnabled(false);
        modifiermat.setText("");
        modifiermat.setEnabled(false);
        listemat.clearSelection();
        
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JRadioButton ajoutermat;
    private javax.swing.JButton confirmer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listemat;
    private javax.swing.JTextField modifiermat;
    private javax.swing.JTextField nommat;
    // End of variables declaration//GEN-END:variables
}
