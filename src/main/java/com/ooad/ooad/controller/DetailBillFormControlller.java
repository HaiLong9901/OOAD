package com.ooad.ooad.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailBillFormControlller  implements Initializable {

    @FXML
    private TextField CreatedAt;

    @FXML
    private TextField EmployId;

    @FXML
    private TextField EquipmentId;

    @FXML
    private TextField billId;

    @FXML
    private TextField totalBill;


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
