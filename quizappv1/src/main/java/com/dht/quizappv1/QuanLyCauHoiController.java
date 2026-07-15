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
import com.dht.services.CategoryServices;
import com.dht.services.FlyWeightfactory;
import com.dht.services.LevelServices;
import com.dht.services.QueryServicesBase;
import com.dht.services.question.QuestionServices;
import com.dht.utils.Configs;
import com.dht.utils.MyAlertSingleton;
import com.dht.utils.MyConnectSingleton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLyCauHoiController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private TableView<Question> tvQuestions;
    @FXML
    private ComboBox<Category> cbSeachCates;
    @FXML
    private ComboBox<Level> cbSeachLevels;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private VBox vChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private TextField txtKeyWords;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadColumns();
        
        
            FlyWeightfactory.getData(Configs.CateServices, Configs.CATE_KEY);
            this.cbCates.setItems(FXCollections.observableList(FlyWeightfactory.getData(Configs.CateServices, Configs.CATE_KEY)));
            this.cbLevels.setItems(FXCollections.observableList(FlyWeightfactory.getData(Configs.LvServices, Configs.LLV_KEY)));
            this.cbSeachCates.setItems(FXCollections.observableList(FlyWeightfactory.getData(Configs.CateServices, Configs.CATE_KEY)));
            this.cbSeachLevels.setItems(FXCollections.observableList(FlyWeightfactory.getData(Configs.LvServices, Configs.LLV_KEY)));

        
        this.txtKeyWords.textProperty().addListener(e -> {
            this.loadTableQuestion();
        });
        this.cbSeachCates.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestion();
        });
        this.cbSeachLevels.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestion();
        });
    }

    private void LoadColumns() {
        TableColumn colid = new TableColumn("Id");
        colid.setCellValueFactory(new PropertyValueFactory("id"));
        colid.setPrefWidth(100);

        TableColumn content = new TableColumn("noi dung cau hoi");
        content.setCellValueFactory(new PropertyValueFactory("content"));
        content.setPrefWidth(80);

        this.tvQuestions.getColumns().addAll(colid, content);
    }

    public void addChoose(ActionEvent e) {
        HBox h = new HBox();
        h.getStyleClass().add("Container");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggle);
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        h.getChildren().addAll(r, txt);
        this.vChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent e) {
        Question q = new Question.Builder().setCategory(this.cbCates.getSelectionModel().getSelectedItem()).setContent(this.txtContent.getText())
                .setLevel(this.cbLevels.getSelectionModel().getSelectedItem()).build();
        List<Choice> choices = new ArrayList<>();
        for (var hbox : this.vChoices.getChildren()) {
            HBox h = (HBox) hbox;
            RadioButton rdo = (RadioButton) h.getChildren().get(0);
            TextField txt = (TextField) h.getChildren().get(1);
            choices.add(new Choice(txt.getText(), rdo.isSelected()));
        }
        try {
            Optional<ButtonType> b = MyAlertSingleton.getInstance().showMsg("Ban chac chan them khong?", Alert.AlertType.CONFIRMATION);
            if (b.isPresent() && b.get() == ButtonType.OK) {
                Configs.UQuestionServices.addQuestion(q, choices);
                MyAlertSingleton.getInstance().showMsg("Them cau hoi thanh cong");

            }

        } catch (SQLException ex) {
            MyAlertSingleton.getInstance().showMsg("Them cau hoi that bai,do" + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadTableQuestion() {
        try {
            QuestionQueryBuilder sql = new QuestionQueryBuilder().widthKeywords(this.txtKeyWords.getText()).widthCategory(this.cbSeachCates.getSelectionModel().getSelectedItem()).widthLevel(this.cbSeachLevels.getSelectionModel().getSelectedItem());
            Configs.QuesServices.setQuery(sql);
            this.tvQuestions.setItems(
                    FXCollections.observableList(
                            Configs.QuesServices.list()
                    )
            );
        } catch (SQLException ex) {
            System.getLogger(QuanLyCauHoiController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
