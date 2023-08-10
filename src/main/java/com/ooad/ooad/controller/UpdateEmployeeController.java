package com.ooad.ooad.controller;

import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {
    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private Button submitBtn;

    private ManagerDao managerDao = new ManagerDao();

    private EmployeeDao employeeDao = new EmployeeDao();
    private AlertMessage alert = new AlertMessage();

    public void updateManager() {
        if(name.getText().isEmpty() || password.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }
        try {
            String empName = name.getText();
            String empPhone = phone.getText();
            String empPass = password.getText();
            String empEmail = email.getText();
            int empId = GlobalState.selectedEmployee.getId();
            Employee employee = new Employee(empId, empName, empPhone, empEmail, empPass);
            employeeDao.updateEmployee(employee);
            alert.successMessage("Cập nhật thành công");

            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        email.setText(GlobalState.selectedEmployee.getEmail());
        phone.setText(GlobalState.selectedEmployee.getPhone());
        password.setText(GlobalState.selectedEmployee.getPassword());
        name.setText(GlobalState.selectedEmployee.getName());
    }
}
