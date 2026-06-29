/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Category;
import com.dht.pojo.Question;
import com.dht.services.CategoryServices;
import com.dht.services.question.QuestionServices;
import com.dht.utils.MyConnectSingleton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLyCauHoiController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private TableView<Question> tvQuestions; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            CategoryServices s = new CategoryServices();
            QuestionServices s2 = new QuestionServices();
            this.LoadColumns();
        try {
            this.cbCates.setItems(FXCollections.observableList(s.getCates()));
            this.tvQuestions.setItems(FXCollections.observableList(s2.getQuestion()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyCauHoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void LoadColumns() {
        TableColumn colid = new TableColumn("Id");
        colid.setCellValueFactory(new PropertyValueFactory("id"));
        colid.setPrefWidth(100);
        
        TableColumn content = new TableColumn("noi dung cau hoi");
        content.setCellValueFactory(new PropertyValueFactory("content"));
        content.setPrefWidth(300);
        
        this.tvQuestions.getColumns().addAll(colid,content);
    }
}
