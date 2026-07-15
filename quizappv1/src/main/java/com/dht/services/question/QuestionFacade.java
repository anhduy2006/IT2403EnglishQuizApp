/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionFacade {
    public static List<Question> getQuestion(QuestionQueryBuilder q) throws SQLException {
        Configs.QuesServices.setQuery(q);
        return Configs.QuesServices.list();
    }
    public static List<Question> getLazyQuestion(QuestionQueryBuilder q) throws SQLException {
        Configs.QuesServices.setQuery(q);
        return new QuestionServicesDecorator(Configs.QuesServices).list();
    }
}
