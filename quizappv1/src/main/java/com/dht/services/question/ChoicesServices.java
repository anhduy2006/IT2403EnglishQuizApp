/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Choice;
import com.dht.utils.MyConnectSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOQ-M
 */
public class ChoicesServices {
        public List<Choice> getChoiceQuestionId(int id) throws SQLException {
        String sql = "SELECT * FROM choice WHERE question_id=?";
        PreparedStatement stm = MyConnectSingleton.getInstance().connect().prepareCall(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            choices.add(new Choice(rs.getInt("id"),rs.getString("content"),rs.getBoolean("is_correct")));
            
        }
        return choices;
    }
}
