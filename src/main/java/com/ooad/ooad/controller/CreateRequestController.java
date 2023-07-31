package com.ooad.ooad.controller;

import com.ooad.ooad.dao.EmployeeDao;
import com.ooad.ooad.dao.EquipmentDao;
import com.ooad.ooad.dao.RequestDao;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.entity.Request;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateRequestController implements Initializable {
    class Status {
        private boolean isActive;
        private String value;

        public Status() {}

        private Status(String value, boolean isActive) {
            this.isActive = isActive;
            this.value = value;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    @FXML
    private TextArea descInput;

    @FXML
    private ComboBox<Equipment> equipComboBox;

    @FXML
    private ComboBox<Status> statusComboBox;

    @FXML
    private Button submitBtn;

    private RequestDao requestDao = new RequestDao();

    private AlertMessage alert = new AlertMessage();
    public void onSubmit() {
        System.out.println("equip: " + equipComboBox.getValue());
        if (equipComboBox.getValue() == null || statusComboBox.getValue() == null || descInput.getText().isEmpty()) {
            alert.errorMessage("Bạn phài điền đầy đủ thông tin");
            return;
        }
        Equipment equipment = equipComboBox.getValue();
        String equipId = equipment.getId();
        Status status = statusComboBox.getValue();
        String desc = descInput.getText();
        try {
            Request request = new Request(GlobalState.loggedinLeader.getId(), equipId, status.isActive, desc);
            requestDao.createRequest(request);
            alert.successMessage("Tạo báo cáo hỏng hóc thành công!");
            submitBtn.getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EquipmentDao equipmentDao = new EquipmentDao();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Equipment> equipmentList = FXCollections.observableArrayList(equipmentDao.getEquipmentByDepId(GlobalState.loggedinLeader.getDepId()));
            equipComboBox.getItems().addAll(equipmentList);
            equipComboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Equipment item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setText(item.getName() + " " + item.getId());
                    } else {
                        setText(null);
                    }
                }
            });

            equipComboBox.setConverter(new StringConverter<Equipment>() {
                @Override
                public String toString(Equipment equipment) {
                    if (equipment == null) {
                        return null;
                    } else {
                        return equipment.getId();
                    }
                }

                @Override
                public Equipment fromString(String s) {
                    return null;
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Status status1 = new Status("Hoạt động", true);
        Status status2 = new Status("Không hoạt động", false);
        statusComboBox.getItems().addAll(status1, status2);
        statusComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getValue());
                } else {
                    setText(null);
                }
            }
        });

        statusComboBox.setConverter(new StringConverter<Status>() {
            @Override
            public String toString(Status status) {
                if (status == null) {
                    return null;
                } else {
                    return status.getValue();
                }
            }

            @Override
            public Status fromString(String s) {
                return null;
            }
        });

    }
}
