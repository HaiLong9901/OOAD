package com.ooad.ooad.controller;
import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailAssignmentFormController implements Initializable {
    @FXML
    private TextField assignId;

    @FXML
    private TextField createdAt;

    @FXML
    private TextField employId;

    @FXML
    private TextField equipmentId;

    @FXML
    private TextField expectation;

    @FXML
    private TextField managerId;

    @FXML
    private TextField requestId;

    @FXML
    private TextField status;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        assignId.setText(Integer.toString(GlobalState.selectedAssignment.getId()));
        requestId.setText(Integer.toString(GlobalState.selectedAssignment.getRequestId()));
        managerId.setText(Integer.toString(GlobalState.selectedAssignment.getManagerId()));
        equipmentId.setText(Integer.toString(GlobalState.selectedAssignment.getEquipId()));
        employId.setText(String.valueOf(GlobalState.selectedAssignment.getEmpId()));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        createdAt.setText(dateFormat.format(GlobalState.selectedAssignment.getCreatedAt()));
        expectation.setText(dateFormat.format(GlobalState.selectedAssignment.getExpectation()));
        status.setText(Integer.toString(GlobalState.selectedAssignment.getStatus()));
    }
}
