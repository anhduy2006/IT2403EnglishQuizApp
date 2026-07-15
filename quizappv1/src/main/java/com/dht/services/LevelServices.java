/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Category;
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
public class LevelServices extends QueryServicesBase<Level>{

    @Override
    public PreparedStatement getStatement() throws SQLException {
        return MyConnectSingleton.getInstance().connect().prepareCall("SELECT * FROM level"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Level getObject(ResultSet rs) throws SQLException {
        return new Level(rs.getInt("id"),rs.getString("name"));// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    public List<Level> getLevels() throws SQLException {
//        Connection connect = MyConnectSingleton.getInstance().connect();//DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");
//            //B3
//            String sql = "SELECT * FROM level";
//            PreparedStatement stm = connect.prepareCall(sql);
//            ResultSet rs = stm.executeQuery(sql);
//            List<Level> levels = new ArrayList<>();
//            while (rs.next()) {
//                int id= rs.getInt("id");
//                String name = rs.getString("name");
//                System.out.printf("%d - %s\n", id,name);
//                levels.add(new Level(id,name));
//            }
//            return levels;
//    }
}
