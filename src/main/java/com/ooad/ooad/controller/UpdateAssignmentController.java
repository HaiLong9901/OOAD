package com.ooad.ooad.controller;

import com.ooad.ooad.dao.AssignmentDao;
import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateAssignmentController implements Initializable {

    class AssignStatus {
        private int status;
        private String text;

        public AssignStatus(int status, String text) {
            this.status = status;
            this.text = text;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    @FXML
    private ComboBox<AssignStatus> status;

    @FXML
    private Button submitBtn;

    private AssignmentDao assignmentDao = new AssignmentDao();

    private AlertMessage alert = new AlertMessage();

    public void onSubmit() {
        if (status.getValue() == null) {
            alert.errorMessage("Bạn phải chọn trạng thái");
            return;
        }
        try {
            assignmentDao.updateAssignStatus(GlobalState.selectedAssignment.getId(), status.getValue().getStatus());
            alert.successMessage("Cập nhật thành công");
            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Đã có lỗi xảy ra");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AssignStatus status1 = new AssignStatus(1, "Chờ nhận viện");
        AssignStatus status2 = new AssignStatus(2, "Đang thực hiện");
        AssignStatus status3 = new AssignStatus(3, "Đã hoàn thành");
        AssignStatus status4 = new AssignStatus(4, "Nhân viên bận");
        AssignStatus status5 = new AssignStatus(5, "Hủy");
        status.getItems().addAll(status1, status2, status3, status4, status5);
        status.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(AssignStatus item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getText());
                } else {
                    setText(null);
                }
            }
        });
        status.setConverter(new StringConverter<>() {
            @Override
            public String toString(AssignStatus status) {
                if (status == null) {
                    return null;
                } else {
                    return status.getText();
                }
            }

            @Override
            public AssignStatus fromString(String s) {
                return null;
            }
        });
    }
}
