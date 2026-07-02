/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.utils;

import com.dht.pojo.Category;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.services.CategoryServices;
import com.dht.services.LevelServices;
import com.dht.services.question.QuestionServices;
import com.dht.services.question.UpdateQuestionServices;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author LOQ-M
 */
public class Configs {
    public final static CategoryServices CateServices = new CategoryServices();
    public final static QuestionServices QuesServices = new QuestionServices();
    public final static LevelServices LvServices = new LevelServices();
    public static final UpdateQuestionServices UQuestionServices = new UpdateQuestionServices();
}
