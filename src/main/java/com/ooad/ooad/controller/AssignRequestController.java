package com.ooad.ooad.controller;

import com.ooad.ooad.dao.AssignmentDao;
import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AssignRequestController implements Initializable {
    @FXML
    private ComboBox<Employee> employeeId;

    @FXML
    private DatePicker expectation;

    @FXML
    private Button submitBtn;

    private AssignmentDao assignmentDao = new AssignmentDao();

    private EmployeeDao employeeDao = new EmployeeDao();

    private AlertMessage alert = new AlertMessage();

    public void onSubmit() {
        if (employeeId.getValue() == null || expectation.getValue() == null) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return ;
        }
        Employee employee = employeeId.getValue();
        Date expect = Date.valueOf(expectation.getValue());

        Assignment assignment = new Assignment(GlobalState.selectedRequest.getId(), GlobalState.loggedInManager.getId(), employee.getId(), 1, expect);
        System.out.println(assignment.toString());

        try {
            assignmentDao.createAssignment(assignment);
            alert.successMessage("Giao việc thành công");
            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            alert.errorMessage("Đã có lỗi xảy ra");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Employee> employeeList = FXCollections.observableArrayList(employeeDao.getAllEmployee());
            employeeId.getItems().addAll(employeeList);
            employeeId.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Employee item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setText(item.getName() + " " + item.getId());
                    } else {
                        setText(null);
                    }
                }
            });

            employeeId.setConverter(new StringConverter<Employee>() {
                @Override
                public String toString(Employee employee) {
                    if (employee == null) {
                        return null;
                    } else {
                        return employee.getEmail();
                    }
                }

                @Override
                public Employee fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
