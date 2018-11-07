/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.prog.projeto.base.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author diego
 */
public class DatabaseConnection {
        
    public static Connection getNewDefaultConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC"); 
        Properties p = new Properties();
        p.setProperty("foreign_keys", "ON");        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:base/dados.db",p);
        conn.setAutoCommit(false);
        return conn;
    }
    
}
