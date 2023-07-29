package com.ooad.ooad.controller;

import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.EquipmentDao;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateEquipmentController implements Initializable {
    @FXML
    private DatePicker expiryInput;

    @FXML
    private TextField idInput;

    @FXML
    private TextField modelInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField priceInput;

    @FXML
    private DatePicker purchaseInput;

    @FXML
    private Button submitBtn;

    private EquipmentDao equipmentDao = new EquipmentDao();

    private AlertMessage alert = new AlertMessage();

    public void onSubmit() {
        if (idInput.getText().isEmpty() || nameInput.getText().isEmpty() || priceInput.getText().isEmpty() || modelInput.getText().isEmpty() || purchaseInput.getValue() == null || expiryInput.getValue() == null) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }
        String id = idInput.getText();
        String name = nameInput.getText();
        String model = modelInput.getText();
        long price = Integer.parseInt(priceInput.getText());
        Date purchase = Date.valueOf(purchaseInput.getValue());
        Date expiry = Date.valueOf(expiryInput.getValue());

        if (purchaseInput.getValue().isAfter(expiryInput.getValue()) || purchaseInput.getValue().isEqual(expiryInput.getValue())) {
            alert.errorMessage("Ngày hết hạn bảo hành phải sau ngày mua");
            return;
        }

        Equipment equipment = new Equipment(id, name, model, purchase, expiry, GlobalState.loggedinLeader.getDepId(), price);
        try {
            equipmentDao.createEquipment(equipment);
            alert.successMessage("Thêm thiết bị thành công");
            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
