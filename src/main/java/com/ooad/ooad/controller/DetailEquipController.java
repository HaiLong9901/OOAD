package com.ooad.ooad.controller;

import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailEquipController implements Initializable {
    @FXML
    private Label address;

    @FXML
    private Label department;

    @FXML
    private Label expiry;

    @FXML
    private Label id;

    @FXML
    private Label model;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label purchase;

    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(GlobalState.selectedEquipment.getName());
        price.setText(Long.toString(GlobalState.selectedEquipment.getPrice()));
        model.setText(GlobalState.selectedEquipment.getModel());
        id.setText(GlobalState.selectedEquipment.getId());
        purchase.setText(GlobalState.selectedEquipment.getPurchase().toString());
        expiry.setText(GlobalState.selectedEquipment.getExpiry().toString());
        try {
            Department dep = departmentDao.getDepartmentById(GlobalState.selectedEquipment.getDepId());
            department.setText(dep.getName());
            address.setText(dep.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
