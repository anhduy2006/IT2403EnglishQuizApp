/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Level;
import com.dht.utils.MyConnectSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class QueryServicesBase<T> {
    public List<T> list() throws SQLException {
        
            
            
            PreparedStatement stm = this.getStatement();
            ResultSet rs = stm.executeQuery();
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(this.getObject(rs));
            }
            return result;
         
    }
    public abstract PreparedStatement getStatement() throws SQLException ;
    public abstract T getObject(ResultSet rs) throws SQLException;
}
