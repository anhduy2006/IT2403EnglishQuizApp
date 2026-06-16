/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anhduy.stil;

import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */
public class AlertSingleton {
    public static AlertSingleton instance;
    private final Alert alert;
    private AlertSingleton() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setTitle("quizApp");
    
    }
    
    public static AlertSingleton getInstance() {
        if (instance == null) {
            instance = new AlertSingleton();
        }
        return instance;
    }
    public void showAlert(String content) {
        this.alert.setContentText(content);
        this.alert.show();
    }
}
