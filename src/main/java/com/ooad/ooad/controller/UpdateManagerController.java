package com.ooad.ooad.controller;

import com.ooad.ooad.dao.ManagerDao;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateManagerController implements Initializable {
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
    private AlertMessage alert = new AlertMessage();

    public void updateManager() {
        if(name.getText().isEmpty() || password.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }
        try {
            String nameManager = name.getText();
            String phoneManager = phone.getText();
            String passwordManager = password.getText();
            String emailManager = email.getText();
            int idManager = GlobalState.selectedManager.getId();
            Manager manager = new Manager(idManager, nameManager, emailManager, phoneManager, passwordManager);
            managerDao.updateManager(manager);
            alert.successMessage("Cập nhật thành công");

            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        email.setText(GlobalState.selectedManager.getEmail());
        phone.setText(GlobalState.selectedManager.getPhone());
        password.setText(GlobalState.selectedManager.getPassword());
        name.setText(GlobalState.selectedManager.getName());
    }
}
