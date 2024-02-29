/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import Dao.DatabaseOperation;
import Dao.ParametreDeConx;
import Dao.ResultSetTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Giovanni
 */
public class ChooseDesignerForProject extends javax.swing.JDialog {

    /**
     * Creates new form ChooseDesignerForProject
     */
    public ChooseDesignerForProject(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tableStyle();
        setLocationRelativeTo(null);

        fillDesignersTable(jTable1);

        fillSelectedDesignersTable(jTable2);
        addASpecificDesignerToProject(jTable1, jTable2, selectedDesignersIds);
    }

    // PROPERTIES
    // Tableau statique des ids des designers sélectionnés pour le projet
    private static List<String> selectedDesignersIds = new ArrayList<>();

    // END OF PROPERTIES
    
    // FUNCTIONS
    
    private void tableStyle() {
        // Style de tableau

        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(new Color(77, 157, 221));

        jTable2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jTable2.getTableHeader().setBackground(new Color(77, 157, 221));

        // Fin style de tableau
    }

    // Afficher les designers non choisis pour le projet
    public void fillDesignersTable(JTable table) {
        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);

        // Récupérer les informations à afficher
        String nomTable = "employe";
        String whereStatement = "deleted_at IS NULL"
                + " AND RespoEmp = \"" + EmployeesResponsibilities.designer + "\"";
        ResultSet rs = operationDb.querySelectAllWhere(nomTable, whereStatement);

        ResultSetTableModel result = new ResultSetTableModel(rs);

        // Modèle qui sera affiché dans la table
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        // Vider le modèle
        tableModel.setRowCount(0);

        // Vérifier s'il y a des utilisateurs enregistrés et les afficher dans la table
        if (result.getRowCount() > 0) {
            for (int i = 0; i < result.getRowCount(); i++) {

                Object[] ligne = new Object[5];

                Object idEmp = result.getValueAt(i, 0).toString();
                Object surnameEmp = result.getValueAt(i, 1).toString();
                Object firstnameEmp = result.getValueAt(i, 2).toString();
                String birthDateEmp = result.getValueAt(i, 3).toString();
                Object genderEmp = result.getValueAt(i, 4).toString();

                ligne[0] = idEmp;
                ligne[1] = surnameEmp + " " + firstnameEmp;
                ligne[2] = genderEmp;

                // Calculer l'âge de l'employé
                String year = birthDateEmp.substring(0, 4);
                String month = birthDateEmp.substring(5, 7);
                String day = birthDateEmp.substring(8, 10);
                int yearInt = Integer.parseInt(year);
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                LocalDate date = LocalDate.of(yearInt, monthInt, dayInt);
                LocalDate currentDateTime = ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate();

                Period period = Period.between(date, currentDateTime);

                int age = period.getYears();

                ligne[3] = age;

                // table.setValueAt(idEmp, i, 0);
                // table.setValueAt(familyName, i, 1);
                // table.setValueAt(firstName, i, 2);
                // table.setValueAt(address, i, 3);
                tableModel.addRow(ligne);
            }

            table.setModel(tableModel);

        }

        operationDb.closeconnexion();
    }

    // Afficher les Designers choisis pour le projet
    public void fillSelectedDesignersTable(JTable table) {
        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);

        // Récupérer les designers affectés au projet
        String nomTable = "designer_projet";
        String whereStatement = "IdProjet = \"" + HomeDesigner.selectedProjectId + "\"";
        ResultSet rs = operationDb.querySelectAllWhere(nomTable, whereStatement);

        ResultSetTableModel result = new ResultSetTableModel(rs);

        // Modèle qui sera affiché dans la table
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        // Vider le modèle
        tableModel.setRowCount(0);

        // Vider la liste des designers affectés au projet
        selectedDesignersIds.clear();

        // Vérifier s'il y a des designers affectés au projet et les afficher dans la table
        if (result.getRowCount() > 0) {

            for (int i = 0; i < result.getRowCount(); i++) {
                // Id du designer
                Object idEmp = result.getValueAt(i, 1).toString();

                // Ajouter l'id à la liste des ids des designers sélectionnés
                selectedDesignersIds.add(idEmp.toString());
            }
        }

        operationDb.closeconnexion();
    }

    private void addDesignerToProjectInDb() {
        // Supprimer tous les liens des designers avec le projet
        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);

        String nomTable = "designer_projet";
        String whereStatement = "IdProjet = \"" + HomeDesigner.selectedProjectId + "\"";
        String rs = operationDb.queryDeleteWhere(nomTable, whereStatement);

        operationDb.closeconnexion();
        // Ajoute les designers au projet
        DefaultTableModel tableModel2 = (DefaultTableModel) jTable2.getModel();
        for (int row = 0; row < tableModel2.getRowCount(); row++) {
            String id = tableModel2.getValueAt(row, 0).toString();

            nomTable = "designer_projet";
            whereStatement = "IdProjet = \"" + HomeDesigner.selectedProjectId + "\"";
            String[] nomColonne = {"IdProjet", "IdDesigner"};
            String[] contenuTableau = {HomeDesigner.selectedProjectId, id};
            rs = operationDb.queryInsertPrecise(nomTable, nomColonne, contenuTableau);
        }
    }

    private void addAllDesignersToProject(JTable table1, JTable table2) {
        // Ajouter tous les designers au projet
        DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
        for (int i = tableModel1.getRowCount() - 1; i >= 0; i--) {
            Object[] rowData = new Object[tableModel1.getColumnCount()];
            for (int j = 0; j < tableModel1.getColumnCount(); j++) {
                rowData[j] = tableModel1.getValueAt(i, j);
            }
            tableModel2.addRow(rowData);
            tableModel1.removeRow(i);
        }
        
        // Mettre à jour les designers affectés au projet dans la bdd
        addDesignerToProjectInDb();
    }

    private void addDesignerToProject(JTable table1, JTable table2) {
        int selectedRow = table1.getSelectedRow();
        DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
        if (selectedRow != -1) {
            // Récupérer les données de la ligne sélectionnée dans la table 1
            Object[] rowData = new Object[tableModel1.getColumnCount()];
            for (int i = 0; i < tableModel1.getColumnCount(); i++) {
                rowData[i] = tableModel1.getValueAt(selectedRow, i);
            }
            // Ajouter les données à la table 2
            tableModel2.addRow(rowData);
            // Supprimer la ligne de la table 1
            tableModel1.removeRow(selectedRow);
        }
        
        // Mettre à jour les designers affectés au projet dans la bdd
        addDesignerToProjectInDb();
    }

    private void addASpecificDesignerToProject(JTable table1, JTable table2, List<String> ids) {
        DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
        for (int row = tableModel1.getRowCount() - 1; row >= 0; row--) {
            String id = (String) tableModel1.getValueAt(row, 0);
            if (ids.contains(id)) {
                // Récupérer les données de la ligne de la table 1
                Object[] rowData = new Object[tableModel1.getColumnCount()];
                for (int i = 0; i < tableModel1.getColumnCount(); i++) {
                    rowData[i] = tableModel1.getValueAt(row, i);
                }
                // Ajouter les données à la table 2
                tableModel2.addRow(rowData);
                // Supprimer la ligne de la table 1
                tableModel1.removeRow(row);
            }
        }
    }

    private void removeAllDesignersFromProject(JTable table1, JTable table2) {
        // Retirer tous les designers du projet
        DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
        for (int i = tableModel2.getRowCount() - 1; i >= 0; i--) {
            Object[] rowData = new Object[tableModel2.getColumnCount()];
            for (int j = 0; j < tableModel2.getColumnCount(); j++) {
                rowData[j] = tableModel2.getValueAt(i, j);
            }
            tableModel1.addRow(rowData);
            tableModel2.removeRow(i);
        }
        
        // Mettre à jour les designers affectés au projet dans la bdd
        addDesignerToProjectInDb();
    }

    private void removeDesignerFromProject(JTable table1, JTable table2) {
        int selectedRow = table2.getSelectedRow();
        DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
        if (selectedRow != -1) {
            // Récupérer les données de la ligne sélectionnée dans la table 2
            Object[] rowData = new Object[tableModel2.getColumnCount()];
            for (int i = 0; i < tableModel2.getColumnCount(); i++) {
                rowData[i] = tableModel2.getValueAt(selectedRow, i);
            }
            // Ajouter les données à la table 1
            tableModel1.addRow(rowData);
            // Supprimer la ligne de la table 2
            tableModel2.removeRow(selectedRow);
        }
        
        // Mettre à jour les designers affectés au projet dans la bdd
        addDesignerToProjectInDb();
    }

    // END OF FUNCTIONS
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choisir les designers du projet");

        jPanel1.setBackground(new java.awt.Color(77, 157, 221));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 700));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Nom & Prénoms", "Genre", "Age"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setFillsViewportHeight(true);
        jTable2.setFocusable(false);
        jTable2.setRowHeight(40);
        jTable2.setRowMargin(2);
        jTable2.setSelectionBackground(new java.awt.Color(77, 157, 221));
        jTable2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowGrid(false);
        jTable2.setShowHorizontalLines(true);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jButton8.setBackground(new java.awt.Color(51, 204, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("< Retirer");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(51, 204, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("<< Retirer Tout");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 700));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Nom & Prénoms", "Genre", "Age"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setRowHeight(40);
        jTable1.setRowMargin(2);
        jTable1.setSelectionBackground(new java.awt.Color(77, 157, 221));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Ajouter tout >>");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 204, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Ajouter >");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        addDesignerToProject(jTable1, jTable2);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        removeDesignerFromProject(jTable1, jTable2);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addAllDesignersToProject(jTable1, jTable2);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        removeAllDesignersFromProject(jTable1, jTable2);
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(ChooseDesignerForProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseDesignerForProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseDesignerForProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseDesignerForProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseDesignerForProject(null, true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
