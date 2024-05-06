/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DbConnectionManager
{
    public static Connection getConnection()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spendingdb","postgres","Suprith@123");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
        
    }
}
