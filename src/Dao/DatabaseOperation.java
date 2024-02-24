/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.net.Socket;
import java.sql.*;

/**
 *
 * @author H
 */
public class DatabaseOperation {

    Connection connection;
    Statement statement;
    String SQL;

    String url;
    String username;
    String password;
    Socket client;

    public DatabaseOperation(String url, String username, String password) {

        this.url = url;
        this.username = username;
        this.password = password;

    }

    public Connection connexionDatabase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println(e);//
        }
        return connection;
    }

    // Fermer la connection
    public Connection closeconnexion() {

        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e);//
        }
        return connection;
    }

    //

    public ResultSet executionQuery(String sql) {
        connexionDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            System.err.println(ex);//
        }
        return resultSet;
    }

    //

    public String executionUpdate(String sql) {
        connexionDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;

        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;

    }

    //
    public ResultSet querySelectAll(String nomTable) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable;
        //System.out.println(SQL);
        return this.executionQuery(SQL);

    }
    //

    public ResultSet querySelectAllWhere(String nomTable, String etat) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + etat;
        return this.executionQuery(SQL);

    }

    // choisir une colonne d une table
    public ResultSet querySelect(String[] nomColonne, String nomTable) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + nomTable;
        return this.executionQuery(SQL);

    }

    public String queryInsert(String nomTable, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + " VALUES(";

        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.executionUpdate(SQL);

    }
    // Fungsi eksekusi query insert

    public String queryInsertPrecise(String nomTable, String[] nomColonne, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        
        System.err.println(SQL);
        return this.executionUpdate(SQL);

    }

    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String etat) {

        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " WHERE " + etat;
        return this.executionUpdate(SQL);

    }

    public String queryDelete(String nomtable) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.executionUpdate(SQL);

    }

    // Overload fungsi eksekusi query delete dengan where
    public String queryDelete(String nomTable, String etat) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + etat;
        return this.executionUpdate(SQL);

    }

}