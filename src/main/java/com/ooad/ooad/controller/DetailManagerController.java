package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailManagerController implements Initializable {

    @FXML
    private Label managerEmail;

    @FXML
    private Label managerId;

    @FXML
    private Label managerName;

    @FXML
    private Label managerPassword;

    @FXML
    private Label managerPhone;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        managerId.setText(Integer.toString(GlobalState.selectedManager.getId()));
        managerPhone.setText(GlobalState.selectedManager.getPhone());
        managerEmail.setText(GlobalState.selectedManager.getEmail());
        managerPassword.setText(GlobalState.selectedManager.getPassword());
        managerName.setText(GlobalState.selectedManager.getName());
    }
}
