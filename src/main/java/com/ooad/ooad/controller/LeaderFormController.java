package com.ooad.ooad.controller;

import com.ooad.ooad.OOADApplication;
import com.ooad.ooad.dao.EquipmentDao;
import com.ooad.ooad.dao.RequestDao;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.entity.Request;
import com.ooad.ooad.shared.GlobalState;
import com.ooad.ooad.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> accDelCol;

    @FXML
    private TableColumn<?, ?> accDetailCol;

    @FXML
    private TableColumn<?, ?> accEmail;

    @FXML
    private TableColumn<?, ?> accId;

    @FXML
    private TableColumn<?, ?> accName;

    @FXML
    private TableColumn<?, ?> accPhoneNum;

    @FXML
    private TableColumn<?, ?> accUpdateCol;

    @FXML
    private AnchorPane accountManagementLayout;

    @FXML
    private AnchorPane adminTaskBar;

    @FXML
    private Button createAccBtn;

    @FXML
    private Button createEquipBtn;

    @FXML
    private Button createReqBtn;

    @FXML
    private TableColumn<Equipment, Void> equipDelete;

    @FXML
    private TableColumn<Equipment, Void> equipUpdate;

    @FXML
    private AnchorPane equipManagementLayout;

    @FXML
    private TableView<Equipment> equipTable;
    @FXML
    private TableColumn<Equipment, String> equipId;

    @FXML
    private TableColumn<Equipment, String> equipName;

    @FXML
    private TableColumn<Equipment, String> equipModel;

    @FXML
    private TableColumn<Equipment, Date> equipPurchase;

    @FXML
    private TableColumn<Equipment, Date> equipExpiry;

    @FXML
    private TableColumn<Equipment, Integer> equipDepId;

    @FXML
    private TableColumn<Equipment, Long> equipPrice;

    @FXML
    private TableColumn<Equipment, Void> equipDetail;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button manageEquipmentBtn;

    @FXML
    private Button manageRequestBtn;

    @FXML
    private TableColumn<Request, Integer> reqCode;

    @FXML
    private TableColumn<Request, Void> reqDetail;

    @FXML
    private TableColumn<Request, String> reqError;

    @FXML
    private TableColumn<Request, Integer> reqId;

    @FXML
    private AnchorPane reqManagementLayout;

    @FXML
    private TableColumn<Request, Integer> reqLeader;

    @FXML
    private TableColumn<Request, Boolean> reqStatus;

    @FXML
    private TableView<Request> reqTable;

    @FXML
    private TableColumn<Request, Date> reqTime;

    @FXML
    private ComboBox<?> rolesComboBox;

    @FXML
    private TableView<?> tableView;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label userNameHeader;

    @FXML
    private Label userNameLabel;

    private AlertMessage alert = new AlertMessage();

    private EquipmentDao equipmentDao = new EquipmentDao();
    private RequestDao requestDao = new RequestDao();

    private Thread equipThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        ObservableList<Equipment> equipmentList = FXCollections.observableArrayList(equipmentDao.getAllEquipment());
                        equipTable.setItems(equipmentList);
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Thread requestThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    try {
                        ObservableList<Request> requestList = FXCollections.observableArrayList(requestDao.getAllRequestByDepId(GlobalState.loggedinLeader.getDepId()));
                        reqTable.setItems(requestList);
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public void redirectToCreateEquipForm() {
        try {
            Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateEquipForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Thêm thiết bị");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToCreateRequestForm() {
        try {
            Parent root = FXMLLoader.load(OOADApplication.class.getResource("CreateRequestForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tạo báo cáo hỏng hóc");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == manageEquipmentBtn) {
            reqManagementLayout.setVisible(false);
            equipManagementLayout.setVisible(true);
        } else if (event.getSource() == manageRequestBtn) {
            reqManagementLayout.setVisible(true);
            equipManagementLayout.setVisible(false);
        }
    }

    public void logout() {
        if (alert.confirmationMessage("Bạn muốn đăng xuất tài khoản này ?")) {
            GlobalState.loggedinLeader = null;
            logoutBtn.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(OOADApplication.class.getResource("login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Đăng nhập");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void displayEquipmentTable()
    {
        equipId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("id"));
        equipName.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
        equipModel.setCellValueFactory(new PropertyValueFactory<Equipment, String>("model"));
        equipPurchase.setCellValueFactory(new PropertyValueFactory<Equipment, Date>("purchase"));

        equipDetail.setCellFactory(new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(TableColumn<Equipment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Equipment equipment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedEquipment = equipmentDao.getEquipmentById(equipment.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("DetailEquipForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Chi tiết thiết bị");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        equipDelete.setCellFactory(new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(TableColumn<Equipment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xóa");
                    {
                        button.setOnAction(event -> {
                            Equipment equipment = getTableView().getItems().get(getIndex());

                            if (alert.confirmationMessage("Bạn muốn xóa thiết bị này?")) {
                                try {
                                    equipmentDao.deleteEquipment(equipment.getId());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        equipUpdate.setCellFactory(new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(TableColumn<Equipment, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Cập nhật");
                    {
                        button.setOnAction(event -> {
                            Equipment equipment = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedEquipment = equipmentDao.getEquipmentById(equipment.getId());
                                try {
                                    Parent root = FXMLLoader.load(OOADApplication.class.getResource("UpdateEquipForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Chi tiết thiết bị");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

    }

    public void displayRequestTable() {
        reqId.setCellValueFactory(new PropertyValueFactory<Request, Integer>("id"));
        reqCode.setCellValueFactory(new PropertyValueFactory<Request, Integer>("equipId"));
        reqError.setCellValueFactory(new PropertyValueFactory<Request, String>("descfault"));
        reqTime.setCellValueFactory(new PropertyValueFactory<Request, Date>("createdAt"));
        reqDetail.setCellFactory(new Callback<TableColumn<Request, Void>, TableCell<Request, Void>>() {
            @Override
            public TableCell<Request, Void> call(TableColumn<Request, Void> leaderVoidTableColumn) {
                return new TableCell<>() {
                    private final Button button = new Button("Xem chi tiết");
                    {
                        button.setOnAction(event -> {
                            Request request = getTableView().getItems().get(getIndex());
                            try {
                                GlobalState.selectedRequest = requestDao.getRequestById(request.getId());
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
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        });

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameHeader.setText(GlobalState.loggedinLeader.getName());
        userNameLabel.setText(GlobalState.loggedinLeader.getName());
        userIdLabel.setText(Integer.toString(GlobalState.loggedinLeader.getId()));
        equipThread.start();
        displayEquipmentTable();
        displayRequestTable();
        requestThread.start();
    }
}
