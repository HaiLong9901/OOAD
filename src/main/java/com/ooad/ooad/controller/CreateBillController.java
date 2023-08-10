package com.ooad.ooad.controller;

import com.ooad.ooad.dao.BillDao;
import com.ooad.ooad.dao.BillDetailDao;
import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.entity.BillDetail;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBillController implements Initializable {
    @FXML
    private Button addComponentBtn;

    @FXML
    private TableView<BillDetail> compinentsTable;

    @FXML
    private TextField component;

    @FXML
    private TableColumn<BillDetail, String> componentCol;

    @FXML
    private TextField equipId;

    @FXML
    private TextField price;

    @FXML
    private TableColumn<BillDetail, Long> priceCol;

    @FXML
    private TableColumn<BillDetail, Integer> quantCol;

    @FXML
    private TextField quantity;

    @FXML
    private Button submitBtn;

    @FXML
    private TableColumn<BillDetail, Long> totalCol;

    @FXML
    private TextField unit;

    @FXML
    private TableColumn<BillDetail, String> unitCol;

    private ObservableList<BillDetail> componentList = FXCollections.observableArrayList();

    private AlertMessage alert = new AlertMessage();

    private BillDao billDao = new BillDao();

    private BillDetailDao billDetailDao = new BillDetailDao();

    public void getComponent() {
        if (component.getText().isEmpty() || price.getText().isEmpty() || quantity.getText().isEmpty() ||unit.getText().isEmpty()) {
            alert.errorMessage("Bạn phải điền đầy đủ thông tin");
            return ;
        }
        try {
            String cpnName = component.getText();
            String cpnUnit = unit.getText();
            int quant = Integer.parseInt(quantity.getText());
            long cpnPrice = Long.parseLong(price.getText());
            BillDetail billDetail = new BillDetail(cpnName, quant, cpnPrice, cpnPrice * quant, cpnUnit);
            componentList.add(billDetail);
            componentList.forEach(cpn -> System.out.println(cpn.toString()));
            displayComponentTable();
            component.setText("");
            unit.setText("");
            quantity.setText("");
            price.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void displayComponentTable() {
        componentCol.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("component"));
        priceCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Long>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Long>("total"));
        quantCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("unit"));
        compinentsTable.setItems(componentList);
    }

    public void onSubmit() {
        if (equipId.getText().isEmpty()) {
            alert.errorMessage("Bạn phải nhập mã thiết bị");
            return;
        }

        if (componentList.size() == 0) {
            alert.errorMessage("Bạn phải thêm linh kiện");
            return;
        }

        try {
            long billTotal = 0;
            for (int i = 0; i < componentList.size(); ++i) {
                billTotal += componentList.get(i).getTotal();
            }
            Bill bill = new Bill(GlobalState.loggedInEmployee.getId(), equipId.getText(), billTotal);
            int billId = billDao.createBill(bill);
            if (billId > 0) {
                for (int i = 0; i < componentList.size(); ++i) {
                    BillDetail billDetail = new BillDetail(billId, componentList.get(i).getComponent(), componentList.get(i).getQuantity(), componentList.get(i).getPrice(), componentList.get(i).getTotal(), componentList.get(i).getUnit());
                    billDetailDao.createBillDetail(billDetail);
                }
                alert.successMessage("Tạo đơn thành công");
                submitBtn.getScene().getWindow().hide();
            } else {
                alert.errorMessage("Tạo đơn thất bại");
                System.out.println("bill: " + billId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
