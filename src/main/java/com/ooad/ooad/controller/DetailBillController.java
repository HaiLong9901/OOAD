package com.ooad.ooad.controller;

import com.ooad.ooad.dao.BillDetailDao;
import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.EquipmentDao;
import com.ooad.ooad.entity.BillDetail;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.shared.GlobalState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DetailBillController implements Initializable {
    @FXML
    private TableColumn<BillDetail, String> componentCol;

    @FXML
    private TableView<BillDetail> componentTable;

    @FXML
    private Label date;

    @FXML
    private Label dep;

    @FXML
    private Label empId;

    @FXML
    private Label empName;

    @FXML
    private Label equipId;

    @FXML
    private Label equipName;

    @FXML
    private TableColumn<BillDetail, Long> grossCol;

    @FXML
    private TableColumn<BillDetail, Long> priceCol;

    @FXML
    private TableColumn<BillDetail, Integer> quantCol;

    @FXML
    private Label total;

    @FXML
    private TableColumn<BillDetail, String> unitCol;

    private EmployeeDao employeeDao = new EmployeeDao();
    private DepartmentDao departmentDao = new DepartmentDao();
    private EquipmentDao equipmentDao = new EquipmentDao();
    private BillDetailDao billDetailDao = new BillDetailDao();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Employee employee = employeeDao.getEmployeeById(GlobalState.selectedBillDetail.getEmpId());
            empName.setText(employee.getName());
            empId.setText(Integer.toString(employee.getId()));

            Equipment equipment = equipmentDao.getEquipmentById(GlobalState.selectedBillDetail.getEqipId());
            equipName.setText(equipment.getName());
            equipId.setText(equipment.getId());

            Department department = departmentDao.getDepartmentById(equipment.getDepId());
            dep.setText(department.getName());

            List<BillDetail> billDetailList = billDetailDao.getBillDetailByBillId(GlobalState.selectedBillDetail.getId());
            ObservableList<BillDetail> billDetailObservableList = FXCollections.observableArrayList(billDetailList);

            componentCol.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("component"));
            grossCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Long>("total"));
            priceCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Long>("price"));
            quantCol.setCellValueFactory(new PropertyValueFactory<BillDetail, Integer>("quantity"));
            unitCol.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("unit"));
            date.setText(GlobalState.selectedBillDetail.getCreatedAt().toString());

            componentTable.setItems(billDetailObservableList);

            total.setText(Long.toString(GlobalState.selectedBillDetail.getTotal()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
