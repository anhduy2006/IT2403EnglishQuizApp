/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Question;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LOQ-M
 */
public class QuestionServicesDecorator implements QuestionServicesBase {
    protected QuestionServicesBase services;

    public QuestionServicesDecorator(QuestionServicesBase services) {
        this.services = services;
    }

    

    @Override
    public List<Question> list() throws SQLException {
        List<Question> questions = this.services.list();
        ChoicesServices choicesSV = new ChoicesServices();
        for (var q:questions) {
            q.setChoices(choicesSV.getChoiceQuestionId(q.getId()));
        }
        return questions;
    }
    
}
