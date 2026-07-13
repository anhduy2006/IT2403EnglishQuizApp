/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.question.QuestionServicesDecorator;
import com.dht.utils.Configs;
import com.dht.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PraticsController implements Initializable {

    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSeachLevel;
    @FXML
    private TextField txtNum;
    @FXML
    private Label lbContent;
    @FXML
    private VBox Vchoices;
    private List<Question> question;
    private int currentIndex = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.CateServices.getCates()));
            this.cbSeachLevel.setItems(FXCollections.observableList(Configs.LvServices.getLevels()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void start(ActionEvent e) {
        QuestionQueryBuilder b;
        b = (QuestionQueryBuilder) new QuestionQueryBuilder().setlimit(this.txtNum.getText()).setOrderby("rand()")
                .widthCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                .widthLevel(this.cbSeachLevel.getSelectionModel().getSelectedItem());
        Configs.QuesServices.setSql(b);

        try {
            this.question = new QuestionServicesDecorator(Configs.QuesServices).getQuestion();
             this.loadQuestion(1);

        } catch (SQLException ex) {
            System.getLogger(PraticsController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void next(ActionEvent e) {
        this.loadQuestion(1);
    }

    public void previous(ActionEvent e) {
        this.loadQuestion(-1);
    }

    private void loadQuestion(int step) {

        this.currentIndex += step;
        if (this.currentIndex >= 0 && this.currentIndex < this.question.size()) {
            Question q = this.question.get(this.currentIndex);
            this.lbContent.setText(q.getContent());
            ToggleGroup t = new ToggleGroup();
            this.Vchoices.getChildren().clear();
            for (var c : q.getChoices()) {
                RadioButton r = new RadioButton(c.getContent());
                r.setToggleGroup(t);
                this.Vchoices.getChildren().add(r);
            }
        }
    }
    public void checkAnswer(ActionEvent e) {
        Question q = this.question.get(this.currentIndex);
        for (int i=0;i<this.Vchoices.getChildren().size();i++) {
            RadioButton r = (RadioButton)this.Vchoices.getChildren().get(i);
            if (r.isSelected()) {
                if (q.getChoices().get(i).isCorrect() == true)
                    MyAlertSingleton.getInstance().showMsg("Chính xác");
                else
                    MyAlertSingleton.getInstance().showMsg("Sai rồi",Alert.AlertType.ERROR);
                break;
            }
        }
    }
}
