package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailEquipDialogController implements Initializable {
    @FXML
    private Label equipmentId;

    @FXML
    private Label equipmentName;

    @FXML
    private Label model;

    @FXML
    private Label purchase;

    @FXML
    private Label expiry;
    @FXML
    private Label price;
    @FXML
    private Label depId;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        equipmentId.setText(Integer.toString(GlobalState.selectedEquipment.getId()));
        equipmentName.setText(GlobalState.selectedEquipment.getName());
        model.setText(GlobalState.selectedEquipment.getModel());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        purchase.setText(dateFormat.format(GlobalState.selectedEquipment.getPurchase()));
        expiry.setText(dateFormat.format(GlobalState.selectedEquipment.getExpiry()));
        price.setText(Long.toString(GlobalState.selectedEquipment.getPrice()));
        depId.setText(Integer.toString(GlobalState.selectedEquipment.getDepId()));
    }
}
