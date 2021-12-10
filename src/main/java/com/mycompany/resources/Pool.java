/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resources;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author JAHAZIEL BH
 */
public class Pool {
    
    private static Pool instance;
    private static Connection conn;

    private Pool() {
        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/postgres";
        try {
            InitialContext cxt = new InitialContext();
            if (cxt == null) {
                throw new Exception("Uh oh -- no context!");
            }
            DataSource ds = (DataSource) cxt.lookup(DATASOURCE_CONTEXT);
            if (ds != null) {
                conn = ds.getConnection();
            } else {
                throw new Exception("Data source not found!");
            }

        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static Pool getInstance() {
        if (conn == null) {
            instance = new Pool();
        }
        return instance;
    }
    
}
