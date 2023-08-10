package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailEmployeeController implements Initializable {

    @FXML
    private Label empEmail;

    @FXML
    private Label empId;

    @FXML
    private Label empName;

    @FXML
    private Label empPassword;

    @FXML
    private Label empPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empEmail.setText(GlobalState.selectedEmployee.getEmail());
        empName.setText(GlobalState.selectedEmployee.getName());
        empPhone.setText(GlobalState.selectedEmployee.getPhone());
        empPassword.setText(GlobalState.selectedEmployee.getPassword());
        empId.setText(Integer.toString(GlobalState.selectedEmployee.getId()));
    }
}
