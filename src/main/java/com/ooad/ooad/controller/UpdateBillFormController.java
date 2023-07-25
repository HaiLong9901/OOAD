package com.ooad.ooad.controller;

import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateBillFormController {
    @FXML
    private TextField CreatedAt;

    @FXML
    private TextField EmployId;

    @FXML
    private TextField EquipmentId;

    @FXML
    private TextField billId;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField totalBill;

    private BillDao billDao = new BillDao();
    private AlertMessage alert = new AlertMessage();

    public void updateBill() {

        if (billId.getText().isEmpty() || EmployId.getText().isEmpty() || EquipmentId.getText().isEmpty() || CreatedAt.getText().isEmpty() || totalBill.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return;
        }

        try {
            int billIdValue = Integer.parseInt(billId.getText());
            int employIdValue = Integer.parseInt(EmployId.getText());
            int equipmentIdValue = Integer.parseInt(EquipmentId.getText());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date createdAtValue = dateFormat.parse(CreatedAt.getText());
            long totalBillValue = Long.parseLong(totalBill.getText());

            Bill bill = new Bill(billIdValue, employIdValue, equipmentIdValue, createdAtValue, totalBillValue);
            billDao.updateBill(bill);

            alert.successMessage("Cập nhật thành công");
            submitBtn.getScene().getWindow().hide();
        } catch (ParseException e) {
            e.printStackTrace();
            alert.errorMessage("Định dạng ngày không hợp lệ. Vui lòng kiểm tra lại.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            alert.errorMessage("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại các trường số.");
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi cập nhật dữ liệu vào cơ sở dữ liệu. Vui lòng thử lại sau hoặc liên hệ với quản trị viên.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bill selectedBill = GlobalState.selectedBill;
        billId.setText(String.valueOf(selectedBill.getBillId()));
        EmployId.setText(String.valueOf(selectedBill.getEmployId()));
        EquipmentId.setText(String.valueOf(selectedBill.getEquipmentId()));

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CreatedAt.setText(dateFormat.format(selectedBill.getCreatedAt()));

        totalBill.setText(String.valueOf(selectedBill.getTotalBill()));
    }



}
