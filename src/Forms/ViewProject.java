/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import Dao.DatabaseOperation;
import Dao.ParametreDeConx;
import Dao.ResultSetTableModel;
import static Forms.AlertSuccess.AlertSuccessMessage;
import static Forms.AlertSuccess.AlertSuccessTitle;
import static Forms.CreateAccount.id;
import static Forms.CreateAccount.pwd;

import static Forms.Home.reloadProjectsTable;
import static Forms.Login.UserId;

import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giovanni
 */
public class ViewProject extends javax.swing.JDialog {

    /**
     * Creates new form ViewProject
     */
    public ViewProject(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        hideErrorMessages();
        changeFormTitle();
        changeFormStateByTitle();

        
    }

    // PROPERTIES
    // Les titres du formulaire
    protected String voirTitre = "Voir un projet";
    protected String ajouterTitre = "Ajouter un projet";
    protected String modifierTitre = "Modifier un projet";

    // Variables statiques des informations du projet
    private static String nom;
    private static String description;
    private static String statut;
    private static File[] images;

    // END OF PROPERTIES
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
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(77, 157, 221));

        jPanel2.setBackground(new java.awt.Color(77, 157, 221));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 700));

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-gestion-de-projet-100.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        jLabel1.setText("Titre du formulaire de projets");

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel2.setText("Nom");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setToolTipText("Entrez votre identifiant");

        jLabel4.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Veuillez renseigner le nom du projet !");

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel3.setText("Description");

        jLabel5.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Veuillez renseigner une description !");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(2);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel6.setText("Statut");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pas encore commencé", "En cours", "Terminé", "Brouillon" }));
        jComboBox1.setBorder(null);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Enregistrer");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Importer");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel7.setText("Images");

        jLabel8.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Ce nom est déjà utilisé pour un autre projet !");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // FUNCTIONS
    private void hideErrorMessages() {
        // Cacher les messages d'erreur
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel8.setVisible(false);
    }
    
    private void changeFormTitle(){
        // Changer le titre du formulaire
        jLabel1.setText(Home.projetTitre);
    }
    
    private void changeFormStateByTitle(){
        // Changer l'état du formulaire en fonction du titre affiché
        if (jLabel1.getText().equals(voirTitre)) {
            // Désactiver les champs afin de ne pouvoir voir que leurs contenus
            jTextField1.setEnabled(false);
            jTextArea1.setEnabled(false);
            jComboBox1.setEnabled(false);
            
            // Changer le bouton d'importation d'image en bouton d'affichage
            jButton3.setText("Afficher les images");

            // Cacher le bouton d'enregistrement
            jButton1.setVisible(false);
        } else if (jLabel1.getText().equals(ajouterTitre)) {
            // Modifier le texte du bouton d'enregistrement à "Ajouter"
            jButton1.setText("Ajouter");
        } else if (jLabel1.getText().equals(modifierTitre)) {
            // Modifier le texte du bouton d'enregistrement à "Modifier"
            jButton1.setText("Modifier");
        }
    }

    private Boolean retrieveFormData() {
        // Vérifier si les champs sont remplis
        if (jTextField1.getText().isBlank()) {
            jLabel4.setVisible(true);
            jLabel8.setVisible(false);
            jLabel5.setVisible(false);
        } else if (jTextArea1.getText().isBlank()) {
            jLabel4.setVisible(false);
            jLabel8.setVisible(true);
            jLabel5.setVisible(false);
        } else {
            hideErrorMessages();

            // Récupérer les informations saisies
            nom = jTextField1.getText().replace("\"", "\\\"");
            description = jTextArea1.getText().replace("\"", "\\\"");
            statut = (String) jComboBox1.getSelectedItem();
            statut = statut.replace("\"", "\\\"");
            return true;
        }

        return false;
    }

    private Boolean verifyIfProjectNameExists(String nom) {
        Boolean found = false;

        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);

        // Vérifier si un projet avec le même nom existe déjà
        String nomTable = "projet";
        String whereStatement = "NomProjet = \"" + nom
                                + "\" AND deleted_at IS NULL";
        ResultSet rs = operationDb.querySelectAllWhere(nomTable, whereStatement);

        ResultSetTableModel result = new ResultSetTableModel(rs);
        if (result.getRowCount() > 0) {
            found = true;
        }
        
        operationDb.closeconnexion();

        return found;
    }
    
    private static String saveImageToDirectory(File imageFile, File destinationDir) throws IOException {
        // Vérifie si le dossier de destination existe, sinon le crée
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        
        // Obtenir le nom du fichier actuel
        String originalFileName = imageFile.getName();
        
        // Obtenir le timestamp actuel
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        
        // Créer le nouveau nom de fichier avec le nom du fichier actuel et le timestamp
        String[] fileNameParts = originalFileName.split("\\."); // Split le nom de fichier par le point pour obtenir l'extension
        String fileExtension = fileNameParts.length > 1 ? "." + fileNameParts[fileNameParts.length - 1] : "";
        String newFileName = fileNameParts[0] + "_" + timestamp + fileExtension;
        
        // Copie le fichier image vers le dossier de destination
        Path sourcePath = imageFile.toPath();
        Path destinationPath = Paths.get(destinationDir.getAbsolutePath(), newFileName);
        Files.copy(sourcePath, destinationPath);

        System.out.println("Image enregistrée dans : " + destinationPath);
        
        return destinationPath.toString();
    }
    
    private static void saveImagePathToDatabase(String savedImagePath) throws SQLException {
        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);
        
        try {
            String nomTable = "image";
            String[] nomColonne = {"Contenu", "IdEmploye"};
            
            String[] contenuTableau = {savedImagePath.replace("\\", "/"), UserId};
            
            operationDb.queryInsertPrecise(nomTable, nomColonne, contenuTableau);
            
            System.out.println("Chemin de l'image enregistré dans la base de données.");
        } catch(Exception ex) {
            System.out.println("Erreur lors de l'enregistrement de l'image dans la bdd : " + ex);
        }
    }

    // END OF FUNCTIONS
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jButton3.getText().equals("Importer")){
            // Si le bouton est "Importer"
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true); // Autorise la sélection multiple

            // Créer un filtre pour n'afficher que les fichiers avec les extensions d'images courantes
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Images", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = fileChooser.getSelectedFiles();

                // Renvoyer les images sélectionnées dans la variable statique appropriée
                images = selectedFiles;

            }
        }else if (jButton3.getText().equals("Afficher les images")){
            // Si le bouton est "Afficher les images"
            
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Si le bouton est "Enregistrer"
        if (jButton1.getText().equals("Ajouter")) {
            // Récupérer les informations du projet
            Boolean retrieve = retrieveFormData();

            if (retrieve) {
                // Vérifier si un même nom de projet existe déjà
                Boolean verify = verifyIfProjectNameExists(nom);
                if (verify) {
                    // Afficher le message d'erreur d'un nom déjà existant
                    jLabel8.setVisible(true);
                } else {
                    // Renseigner les informations de la bdd
                    String url = ParametreDeConx.HOST_DB;
                    String username = ParametreDeConx.USERNAME_DB;
                    String password = ParametreDeConx.PASSWORD_DB;
                    DatabaseOperation operationDb = new DatabaseOperation(url, username, password);
                    
                    try {
                        //// Enregistrement du nouveau projet
                        String nomTable = "projet";
                        String[] nomColonne = {"NomProjet", "DescriptionProjet", "StatutProjet", "IdCreateur", "created_at", "updated_at"};

                        // Obtenir la date et l'heure actuelles avec un fuseau horaire spécifique (par exemple, UTC)
                        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        String currentDateTimeFormattedString = currentDateTime.format(formatter);

                        String[] contenuTableau = {nom, description, statut, Login.UserId, currentDateTimeFormattedString, currentDateTimeFormattedString};

                        System.out.println(Login.UserId + " " + Login.UserName);

                        // Appliquer la requête d'insertion
                        String createProject = operationDb.queryInsertPrecise(nomTable, nomColonne, contenuTableau);
                        //// Fin Enregistrement du nouveau projet
                        
                        //// Enregistrement des images liées au projet
                        if(images.length > 0){
                            for (File image : images){
                                // Enregistrer les images dans le dossier "projectimages"
                                // Et enregistrer les chemins des images dans la bdd
                                String destinationFolder = "D:/Cours 3e année/JAVA/Projet/GestionIndustrieTextile/src/projectimages";

                                File destinationDir = new File(destinationFolder);

                                try {
                                    String savedImagePath;
                                    // Sauvegarder l'image dans le dossier
                                    savedImagePath = saveImageToDirectory(image, destinationDir);
                                    // Sauvegarder le chemin de l'image dans la bdd
                                    saveImagePathToDatabase(savedImagePath);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (IOException ex) {
                                    Logger.getLogger(ViewProject.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        }
                        //// Fin Enregistrement des images liées au projet

                        // Vider les champs et ramener le curseur au premier champ
                        jTextField1.setText("");
                        jTextArea1.setText("");
                        jTextField1.requestFocusInWindow();

                        // Raffraîchir la liste des projets
                        reloadProjectsTable = true;

                        // Message de succès de l'enregistrement du projet
                        AlertSuccessTitle = "Enregistré";
                        AlertSuccessMessage = "Projet enregistré avec succès !";

                        // Afficher le message de succès d'enregistrement
                        Home home = new Home();
                        AlertSuccess alert = new AlertSuccess(home, true);
                        alert.setVisible(true);

                        operationDb.closeconnexion();
                    } catch (SQLException ex) {
                        operationDb.closeconnexion();
                        Logger.getLogger(ViewProject.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        } else if (jButton1.getText().equals("Modifier")) {
            // Si le bouton est "Modifier" faire la modification du projet sélectionné
        }

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
            java.util.logging.Logger.getLogger(ViewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProject(null, true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
