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
import com.dht.services.QueryServicesBase;
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
public class QuestionServices extends QueryServicesBase<Question> implements QuestionServicesBase{
    private QuestionQueryBuilder sql;

    public QuestionServices() {
    }

    public QuestionServices(QuestionQueryBuilder query) {
        this.sql = query;
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

    @Override
    public PreparedStatement getStatement() throws SQLException {
        return this.sql.build();  // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Question getObject(ResultSet rs) throws SQLException {
        return new Question.Builder().setId(rs.getInt("id")).setContent(rs.getString("content")).build(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
