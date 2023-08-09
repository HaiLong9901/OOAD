package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.dao.RequestDao;
import com.ooad.ooad.entity.Request;
import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailAssignmentController implements Initializable {
    @FXML
    private Label assignId;

    @FXML
    private Label createdAt;

    @FXML
    private Label employee;

    @FXML
    private Label expectation;

    @FXML
    private Label managerId;

    @FXML
    private Label requestId;

    @FXML
    private Label status;

    @FXML
    private Button updateBtn;

    @FXML
    private Button detailRequestBtn;

    private EmployeeDao employeeDao = new EmployeeDao();
    private ManagerDao managerDao = new ManagerDao();

    private RequestDao requestDao = new RequestDao();

    public void onClick() {
        try {
            Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateAssignmentForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Cập nhập trạng thái phiếu giao việc");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateBtn.getScene().getWindow().hide();
    }

    public void seeDetail() {
        try {
            GlobalState.selectedRequest = requestDao.getRequestById(GlobalState.selectedAssignment.getRequestId());
            try {
                Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailReqForm.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Chi tiết báo cáo hỏng hóc");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createdAt.setText(GlobalState.selectedAssignment.getCreatedAt().toString());
        expectation.setText(GlobalState.selectedAssignment.getExpectation().toString());
        assignId.setText(Integer.toString(GlobalState.selectedAssignment.getId()));
        if (GlobalState.selectedAssignment.getStatus() == 1) {
            status.setText("Chờ nhận việc");
        } else if (GlobalState.selectedAssignment.getStatus() == 2) {
            status.setText("Đang thực hiện");
        } else if (GlobalState.selectedAssignment.getStatus() == 3) {
            status.setText("Đã hoàn thành");
        } else if (GlobalState.selectedAssignment.getStatus() == 4) {
            status.setText("Nhân viên bận");
        } else if (GlobalState.selectedAssignment.getStatus() == 5) {
            status.setText("Hủy");
        }
        requestId.setText(Integer.toString(GlobalState.selectedAssignment.getRequestId()));
        try {
            managerId.setText(managerDao.getManagerById(GlobalState.selectedAssignment.getManagerId()).getName());
            employee.setText(employeeDao.getEmployeeById(GlobalState.selectedAssignment.getEmpId()).getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
