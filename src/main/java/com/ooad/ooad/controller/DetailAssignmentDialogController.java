package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailAssignmentDialogController implements Initializable {
    @FXML
    private Label assignId;
    @FXML
    private Label requestId;

    @FXML
    private Label managerId;

    @FXML
    private Label equipmentId;

    @FXML
    private Label employId;

    @FXML
    private Label createdAt;
    @FXML
    private Label expectation;
    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        assignId.setText(Integer.toString(GlobalState.selectedAssignment.getId()));
        requestId.setText(Integer.toString(GlobalState.selectedAssignment.getRequestId()));
        managerId.setText(Integer.toString(GlobalState.selectedAssignment.getManagerId()));
        equipmentId.setText(Integer.toString(GlobalState.selectedAssignment.getEquipId()));
        employId.setText(GlobalState.selectedAssignment.getEmpId());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        createdAt.setText(dateFormat.format(GlobalState.selectedAssignment.getCreatedAt()));
        expectation.setText(dateFormat.format(GlobalState.selectedAssignment.getExpectation()));
        status.setText(Integer.toString(GlobalState.selectedAssignment.getStatus()));
    }
}
