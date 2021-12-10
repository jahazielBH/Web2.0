/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Conexion {
    private static Conexion instance;
    private static Connection conn;
    
    private Conexion() {
        String urlDatabase = "jdbc:postgresql://localhost:5432/uv";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,"postgres","postgres");
            System.out.println("CONEXION EXITOSA");
        } catch (Exception e) {
            System.out.println("ERROR EN LA CONEXION:" + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static Conexion getInstance() {
        if (conn == null) {
            instance = new Conexion();
        }
        return instance;
    }
    
    public boolean execute(String sql) {
        boolean res = false;
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
}
