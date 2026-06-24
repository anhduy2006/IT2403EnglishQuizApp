/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Category;
import com.dht.services.CategoryServices;
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

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLyCauHoiController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            CategoryServices s = new CategoryServices();
        try {
            this.cbCates.setItems(FXCollections.observableList(s.getCates()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyCauHoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
