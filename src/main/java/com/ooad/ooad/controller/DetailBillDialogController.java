package com.ooad.ooad.controller;

import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailBillDialogController implements Initializable {
    @FXML
    private Label EmployId;
    @FXML
    private Label billId;

    @FXML
    private Label EquipmentId;

    @FXML
    private Label CreatedAt;

    @FXML
    private Label totalBill;



    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        billId.setText(Integer.toString(GlobalState.selectedBill.getId()));
        EmployId.setText(Integer.toString(GlobalState.selectedBill.getEmpId()));
        EquipmentId.setText((GlobalState.selectedBill.getEquipId()));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CreatedAt.setText(dateFormat.format(GlobalState.selectedBill.getCreatedAt()));
        totalBill.setText(Long.toString(GlobalState.selectedBill.getTotal()));
    }
}
