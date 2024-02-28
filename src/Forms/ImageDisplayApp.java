/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import Dao.DatabaseOperation;
import Dao.ParametreDeConx;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author Giovanni
 */
public class ImageDisplayApp extends javax.swing.JFrame {

    /**
     * Creates new form ImageDisplayApp
     */
    public ImageDisplayApp() {
        setTitle("Images from Database");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);

        JPanel panel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(panel);

        // Récupérer les chemins des images depuis la base de données
        String[] imagePaths = getImagePathsFromDatabase();

        // Charger et afficher les images dans des JLabels avec une taille adaptative
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        for (String path : imagePaths) {
            JLabel label = new JLabel();
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            Image scaledImage = getScaledImage(image, 200, 200); // Redimensionner l'image pour l'affichage initial
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            label.setIcon(scaledIcon);
            label.setPreferredSize(new Dimension(600, 400)); // Définir la taille préférée du JLabel
            panel.add(label, gbc);
            gbc.gridy++;
        }

        getContentPane().add(scrollPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // FUNCTIONS
    
    private Image getScaledImage(Image srcImg, int width, int height) {
        int originalWidth = srcImg.getWidth(null);
        int originalHeight = srcImg.getHeight(null);

        double ratio = 1.0 * width / originalWidth;
        if (originalWidth > originalHeight) {
            ratio = 1.0 * height / originalHeight;
        }

        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, newWidth, newHeight, null);
        g2.dispose();

        return resizedImg;
    }
    
    private String[] getImagePathsFromDatabase() {
        String[] imagePaths = new String[0];
        ResultSet rs = null;
        
        // Renseigner les informations de la bdd
        String url = ParametreDeConx.HOST_DB;
        String username = ParametreDeConx.USERNAME_DB;
        String password = ParametreDeConx.PASSWORD_DB;
        DatabaseOperation operationDb = new DatabaseOperation(url, username, password);

        try {
            // Exécuter une requête SQL pour récupérer les chemins des images
            String nomTable = "image";
            rs = operationDb.querySelectAll(nomTable);
            
            // Stocker les chemins des images dans un tableau
            java.util.List<String> imagePathList = new java.util.ArrayList<>();
            while (rs.next()) {
                imagePathList.add(rs.getString("Contenu"));
            }
            imagePaths = imagePathList.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources JDBC
            operationDb.closeconnexion();
        }

        return imagePaths;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ImageDisplayApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageDisplayApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageDisplayApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageDisplayApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageDisplayApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}