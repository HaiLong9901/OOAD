package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailRequestDialogController implements Initializable {
    @FXML
    private Label requestId;

    @FXML
    private Label leaderId;

    @FXML
    private Label equipId;

    @FXML
    private Label isActive;

    @FXML
    private Label descfault;
    @FXML
    private Label createdat;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        requestId.setText(Integer.toString(GlobalState.selectedRequest.getId()));
        leaderId.setText(Integer.toString(GlobalState.selectedRequest.getLeader()));
        equipId.setText(GlobalState.selectedRequest.getEquipId());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        createdat.setText(dateFormat.format(GlobalState.selectedRequest.getCreatedAt()));
        descfault.setText(GlobalState.selectedRequest.getDescfault());
    }
}
