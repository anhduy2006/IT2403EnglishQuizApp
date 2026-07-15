/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Question;
import com.dht.services.exam.ExamStragely;
import com.dht.services.exam.ExamType;
import com.dht.services.exam.FixedExam;
import com.dht.services.exam.SpecificExam;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {

    @FXML
    ComboBox<ExamType> cbExam;
    @FXML
    TextField txtNum;
    @FXML
    ListView<Question> cauHoiKiemTra;
    private List<Question> question;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExam.setItems(FXCollections.observableArrayList(ExamType.values()));
    }

    public void start(ActionEvent e) {
        switch (this.cbExam.getSelectionModel().getSelectedItem()) {
            case SPECIFIC:
                ExamStragely s = new SpecificExam(this.txtNum.getText());
                this.question = s.getQuestion();
                this.cauHoiKiemTra.setItems(FXCollections.observableList(this.question));
                break;
            default:
                ExamStragely s1 = new FixedExam();
                this.question = s1.getQuestion();

        }
        this.cauHoiKiemTra.setItems(FXCollections.observableList(this.question));
    }
}
