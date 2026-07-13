/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
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
public class QuestionServices extends QuestionServicesBase{
    private QuestionQueryBuilder sql;

    public QuestionServices() {
    }

    public QuestionServices(QuestionQueryBuilder query) {
        this.sql = query;
    }
    
    @Override
    public List<Question> getQuestion() throws SQLException {
        

        PreparedStatement stm = this.sql.build();

        ResultSet rs = stm.executeQuery();
        List<Question> ques = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String Content = rs.getString("content");
            ques.add(new Question.Builder().setId(id).setContent(Content).build());

        }
        return ques;
    }


    /**
     * @param query the query to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.sql = query;
    }
    public void setSql(QuestionQueryBuilder sql) {
        this.sql = sql;
    }
}
