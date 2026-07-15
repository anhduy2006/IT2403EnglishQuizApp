/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.exam;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.utils.Configs;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SpecificExam extends ExamStragely{
    private int num;

    public SpecificExam(int num) {
        this.num = num;
    }
    public SpecificExam(String num) {
        this(Integer.parseInt(num));
    }
    
    @Override
    public List<Question> getQuestion() {
        QuestionQueryBuilder q = new QuestionQueryBuilder().setlimit(num).setOrderby("rand()");
        
        Configs.QuesServices.setQuery(q);
        try {
            return Configs.QuesServices.list();
        } catch (SQLException ex) {
            Logger.getLogger(SpecificExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
