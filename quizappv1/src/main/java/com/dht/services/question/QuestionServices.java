/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Category;
import com.dht.pojo.Question;
import com.dht.utils.MyConnectSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOQ-M
 */
public class QuestionServices {
    public  List<Question> getQuestion() throws SQLException {
        Connection connect = MyConnectSingleton.getInstance().connect();//DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");
            //B3
            String sql = "SELECT * FROM question";
            PreparedStatement stm = connect.prepareCall(sql);
            ResultSet rs = stm.executeQuery(sql);
            List<Question> ques = new ArrayList<>();
            while (rs.next()) {
                int id= rs.getInt("id");
                String Content = rs.getString("content");
                ques.add(new Question.Builder().setId(id).setContent(Content).build());
                
            }
            return ques;
    }
}
