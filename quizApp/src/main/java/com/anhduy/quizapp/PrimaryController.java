package com.anhduy.quizapp;
import com.anhduy.stil.AlertSingleton;
import com.anhduy.stil.themes.Themes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
public class PrimaryController implements Initializable{
    @FXML private ComboBox<Themes> cbThemes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Themes.values()));
    }
    public void manageQuestion(ActionEvent e) {
        AlertSingleton.getInstance().showAlert("Comming soon");
        
    }
    public void practice(ActionEvent e) {
        AlertSingleton.getInstance().showAlert("Comming soon");
    
    }
    public void exam(ActionEvent e) {
        AlertSingleton.getInstance().showAlert("Comming soon");
    
    }
   

    
    
}
