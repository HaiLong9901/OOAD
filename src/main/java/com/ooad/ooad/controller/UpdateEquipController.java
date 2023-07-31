package com.ooad.ooad.controller;

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
import java.time.LocalDate;
import java.time.ZoneId;
//import java.util.Date;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateEquipController implements Initializable {

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
        if (idInput.getText().isEmpty() || nameInput.getText().isEmpty() || priceInput.getText().isEmpty() || modelInput.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }
        String id = idInput.getText();
        String name = nameInput.getText();
        String model = modelInput.getText();
        long price = Integer.parseInt(priceInput.getText());
        Date purchase;
        Date expiry;

        if (purchaseInput.getValue() == null) {
            purchase = (Date) GlobalState.selectedEquipment.getPurchase();
        } else {
            purchase = Date.valueOf(purchaseInput.getValue());
        }

        if (expiryInput.getValue() == null) {
            expiry = (Date) GlobalState.selectedEquipment.getExpiry();
        } else {
            expiry = Date.valueOf(expiryInput.getValue());
        }

        if (purchase.after(expiry) || purchase.equals(expiry)) {
            alert.errorMessage("Ngày hết hạn bảo hành phải sau ngày mua");
            return;
        }

        Equipment equipment = new Equipment(id, name, model, purchase, expiry, GlobalState.loggedinLeader.getDepId(), price);
        try {
            equipmentDao.updateEquipment(equipment);
            alert.successMessage("Cập nhật thiết bị thành công");
            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idInput.setText(GlobalState.selectedEquipment.getId());
        idInput.setDisable(true);
        nameInput.setText((GlobalState.selectedEquipment.getName()));
        modelInput.setText(GlobalState.selectedEquipment.getModel());
        priceInput.setText(Integer.toString((int) GlobalState.selectedEquipment.getPrice()));
//        purchaseInput.setValue(GlobalState.selectedEquipment.getPurchase().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        expiryInput.setValue(GlobalState.selectedEquipment.getExpiry().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
