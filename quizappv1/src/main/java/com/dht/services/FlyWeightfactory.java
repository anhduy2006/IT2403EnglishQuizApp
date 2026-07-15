/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlyWeightfactory {
    public static final Map<String,List> catchedData = new HashMap<>();
    
    public static <E> List<E> getData(QueryServicesBase ser,String key) {
        if (catchedData.containsKey(key)==false) {
            try {
                catchedData.put(key, ser.list());
            } catch (SQLException ex) {
                Logger.getLogger(FlyWeightfactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return catchedData.get(key);
    }
}
