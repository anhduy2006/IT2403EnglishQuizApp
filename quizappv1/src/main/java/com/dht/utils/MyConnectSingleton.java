/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class MyConnectSingleton {
    private static MyConnectSingleton instance;
    private Connection connect;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnectSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private MyConnectSingleton() {
        try {
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "123456");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnectSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static MyConnectSingleton getInstance() {
        if (instance == null)
            instance = new MyConnectSingleton();
        return instance;
    }
    public Connection connect() {
        return this.connect;
    }
    public void Close() throws SQLException {
        if (this.connect != null)
            this.connect.close();
    } 
}
