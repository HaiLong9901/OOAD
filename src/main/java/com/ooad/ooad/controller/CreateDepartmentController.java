package com.ooad.ooad.controller;

import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateDepartmentController implements Initializable {

    @FXML
    private Button createBtn;

    @FXML
    private TextField depAddressInput;

    @FXML
    private TextField depNameInput;

    private AlertMessage alert = new AlertMessage();

    private DepartmentDao departmentDao = new DepartmentDao();

    public void createDep() {
        if(depAddressInput.getText().isEmpty() || depNameInput.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
        }
        String name = depNameInput.getText();
        String address = depAddressInput.getText();

        Department department = new Department(name, address);

        try {
            departmentDao.createDepartment(department);
            alert.successMessage("Thêm phòng ban thành công");
            createBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            alert.errorMessage("Thêm phòng ban không thành công");
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

    }

}
