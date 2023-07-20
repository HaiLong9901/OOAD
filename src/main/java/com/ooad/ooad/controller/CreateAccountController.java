package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.LeaderDao;
import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Leader;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    private ComboBox<Department> depsComboBox;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField passInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private ComboBox<String> rolesComboBox;

    @FXML
    private Button submitBtn;

    private ManagerDao managerDao = new ManagerDao();
    private EmployeeDao employeeDao = new EmployeeDao();

    private DepartmentDao departmentDao = new DepartmentDao();
    private LeaderDao leaderDao = new LeaderDao();
    private AlertMessage alert = new AlertMessage();
    private ObservableList<String> roleList = FXCollections.observableArrayList("manager", "leader", "employee");
    private ObservableList<Department> departmentsList;

    public void createAccount() {
        if (nameInput.getText().isEmpty() || phoneInput.getText().isEmpty() || emailInput.getText().isEmpty() || passInput.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }
        String role = rolesComboBox.getValue();
        if (role == null || role.length() == 0) {
            alert.errorMessage("Bạn phải chọn vai trò cho tài khoản");
            return;
        }
        String name = nameInput.getText();
        String phone = phoneInput.getText();
        String email = emailInput.getText();
        String password = passInput.getText();
        if (role.equals("manager")) {
            Manager manager = new Manager(name, email, phone, password);
            try {
                managerDao.createManagerAccount(manager);
                alert.successMessage("Tạo tài khoản thành công");
                GlobalState.managersList = FXCollections.observableArrayList(managerDao.getAllManager());
                submitBtn.getScene().getWindow().hide();
            } catch (SQLException e) {
                alert.errorMessage("Đã có lỗi xảy ra, vui lòng thử lại sau!");
                e.printStackTrace();
            }
        }
        else if (role.equals("employee")) {
            Employee employee = new Employee(name, phone, email, password);
            try {
                employeeDao.createEmployee(employee);
                alert.successMessage("Tạo tài khoản thành công");
                submitBtn.getScene().getWindow().hide();
            } catch (SQLException e) {
                alert.errorMessage("Đã có lỗi xảy ra, vui lòng thử lại sau!");
                e.printStackTrace();
            }
        } else {
            Department dep = depsComboBox.getValue();
            if (dep == null) {
                alert.errorMessage("Bạn phải chọn phòng ban cho trưởng phòng");
                return;
            }
            Leader leader = new Leader(name, phone, email, password, dep.getId());

            try {
                leaderDao.createLeader(leader);
                alert.successMessage("Tạo tài khoản thành công");
                submitBtn.getScene().getWindow().hide();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onChangeRole() {
        String role = rolesComboBox.getValue();
        if (role.equals("manager") || role.equals("employee")) {
            depsComboBox.setVisible(false);
        } else {
            depsComboBox.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        rolesComboBox.setItems(roleList);
        try {
            departmentsList = FXCollections.observableArrayList(departmentDao.getAllDepartment());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        depsComboBox.setItems(departmentsList);
    }
}
