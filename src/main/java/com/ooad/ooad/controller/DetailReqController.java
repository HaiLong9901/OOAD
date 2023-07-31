package com.ooad.ooad.controller;

import com.ooad.ooad.dao.DepartmentDao;
import com.ooad.ooad.dao.EquipmentDao;
import com.ooad.ooad.dao.LeaderDao;
import com.ooad.ooad.entity.Department;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.entity.Leader;
import com.ooad.ooad.shared.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailReqController implements Initializable {

    @FXML
    private Label depAddress;

    @FXML
    private Label depName;

    @FXML
    private Text descFault;

    @FXML
    private Label equipId;

    @FXML
    private Label equipModel;

    @FXML
    private Label equipName;

    @FXML
    private Label equipStatus;

    @FXML
    private Label leaderName;

    @FXML
    private Label leaderPhone;

    @FXML
    private Label reqId;

    private LeaderDao leaderDao = new LeaderDao();
    private EquipmentDao equipmentDao = new EquipmentDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reqId.setText(Integer.toString(GlobalState.selectedRequest.getId()));
        descFault.setText(GlobalState.selectedRequest.getDescfault());
        equipStatus.setText(GlobalState.selectedRequest.isEquipActive() ? "Vẫn hoạt động" : "Không hoạt động");
        try {
            Leader leader = leaderDao.getLeaderById(GlobalState.selectedRequest.getLeader());
            leaderName.setText(leader.getName());
            leaderPhone.setText(leader.getPhone());
            Department department = departmentDao.getDepartmentById(leader.getDepId());
            depName.setText(department.getName());
            depAddress.setText(department.getAddress());
            Equipment equipment = equipmentDao.getEquipmentById(GlobalState.selectedRequest.getEquipId());
            equipId.setText(equipment.getId());
            equipName.setText(equipment.getName());
            equipModel.setText(equipment.getModel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
