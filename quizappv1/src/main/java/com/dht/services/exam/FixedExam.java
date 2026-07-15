/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.exam;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.question.QuestionFacade;
import com.dht.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FixedExam extends ExamStragely{

    @Override
    public List<Question> getQuestion() {
        List<Question> questions = new ArrayList<>();
        for (int i=0;i< Configs.RATES.length;i++) {
            QuestionQueryBuilder q = new QuestionQueryBuilder().widthLevel(i+1).
                    setOrderby("rand()").setlimit((int)Configs.RATES[i] *Configs.EXAM_NUM);
            try {
                questions.addAll(QuestionFacade.getLazyQuestion(q));
            } catch (SQLException ex) {
                Logger.getLogger(FixedExam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return questions;
    }
    
}
